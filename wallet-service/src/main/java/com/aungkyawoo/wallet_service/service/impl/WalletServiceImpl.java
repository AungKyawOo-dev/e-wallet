package com.aungkyawoo.wallet_service.service.impl;

import com.aungkyawoo.wallet_service.client.UserClient;
import com.aungkyawoo.wallet_service.constants.TransactionType;
import com.aungkyawoo.wallet_service.dto.WalletDto;
import com.aungkyawoo.wallet_service.dto.request.DepositRequestDto;
import com.aungkyawoo.wallet_service.dto.request.WithdrawRequestDto;
import com.aungkyawoo.wallet_service.entity.Transactions;
import com.aungkyawoo.wallet_service.entity.Wallet;
import com.aungkyawoo.wallet_service.exception.DuplicateTransactionException;
import com.aungkyawoo.wallet_service.exception.InsufficientBalanceException;
import com.aungkyawoo.wallet_service.exception.WalletNotFoundException;
import com.aungkyawoo.wallet_service.exception.WalletAlreadyExistsException;
import com.aungkyawoo.wallet_service.mapper.WalletMapper;
import com.aungkyawoo.wallet_service.repository.TransactionRepository;
import com.aungkyawoo.wallet_service.repository.WalletRepository;
import com.aungkyawoo.wallet_service.service.IWalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.aungkyawoo.wallet_service.constants.CurrencyConstants.DEFAULT_CURRENCY;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements IWalletService {

    private final UserClient userClient;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public WalletDto getWalletBalance(String userId, String currency) {
        log.info("getWalletBalance with userId: {}, currency: {}", userId, currency);
        validateUser(userId);
        Wallet wallet = walletRepository.findByUserId(userId).orElseGet(() ->
                Wallet.builder()
                        .userId(userId)
                        .balance(BigDecimal.ZERO)
                        .currency(currency)
                        .build()
        );

        return WalletMapper.toWalletDto(wallet);
    }

    @Override
    public WalletDto initializeWallet(String userId) {
        validateUser(userId);
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if(wallet.isPresent()) {
            throw new WalletAlreadyExistsException("Wallet already exists for user: " + userId);
        }

        Wallet wallet1 = Wallet.builder().userId(userId).balance(BigDecimal.ZERO).currency(DEFAULT_CURRENCY).build();
        walletRepository.save(wallet1);
        return WalletMapper.toWalletDto(wallet1);
    }

    @Transactional
    @Override
    public WalletDto deposit(DepositRequestDto depositRequestDto) {
        validateUser(depositRequestDto.getUserId());

        if (transactionRepository.existsByReference(depositRequestDto.getReference())) {
            throw new DuplicateTransactionException("Transaction already processed: " + depositRequestDto.getReference());
        }

        Wallet wallet = walletRepository.findByUserIdAndCurrency(
                depositRequestDto.getUserId(),
                depositRequestDto.getCurrency()
        ).orElseGet(() ->
                Wallet.builder()
                        .userId(depositRequestDto.getUserId())
                        .balance(BigDecimal.ZERO)
                        .currency(depositRequestDto.getCurrency())
                        .build()
        );

        wallet.setBalance(wallet.getBalance().add(depositRequestDto.getAmount()));
        walletRepository.save(wallet);

        Transactions transaction = Transactions.builder()
                .fromUserId(depositRequestDto.getUserId())
                .toUserId(depositRequestDto.getUserId())
                .amount(depositRequestDto.getAmount())
                .currency(depositRequestDto.getCurrency())
                .type(TransactionType.DEPOSIT)
                .reference(depositRequestDto.getReference())
                .build();
        transactionRepository.save(transaction);

        return WalletMapper.toWalletDto(wallet);
    }

    @Transactional
    @Override
    public WalletDto widthdraw(WithdrawRequestDto withdrawRequestDto) {
        validateUser(withdrawRequestDto.getUserId());

        if (transactionRepository.existsByReference(withdrawRequestDto.getReference())) {
            throw new DuplicateTransactionException("Transaction already processed: " + withdrawRequestDto.getReference());
        }

        Wallet wallet = walletRepository.findByUserIdAndCurrency(
                withdrawRequestDto.getUserId(),
                withdrawRequestDto.getCurrency()
        ).orElseThrow(() -> new InsufficientBalanceException("Insufficient balance to withdraw: " + withdrawRequestDto.getAmount() + " " + withdrawRequestDto.getCurrency()));

        if (wallet.getBalance().compareTo(withdrawRequestDto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to withdraw: " + withdrawRequestDto.getAmount() + " " + withdrawRequestDto.getCurrency());
        }

        wallet.setBalance(wallet.getBalance().subtract(withdrawRequestDto.getAmount()));
        walletRepository.save(wallet);

        Transactions transaction = Transactions.builder()
                .fromUserId(withdrawRequestDto.getUserId())
                .toUserId(withdrawRequestDto.getUserId())
                .amount(withdrawRequestDto.getAmount())
                .currency(withdrawRequestDto.getCurrency())
                .type(TransactionType.WITHDRAW)
                .reference(withdrawRequestDto.getReference())
                .build();
        transactionRepository.save(transaction);

        // TODO: invoke external service for withdraw
        // TODO: send notification
        return WalletMapper.toWalletDto(wallet);
    }

    private void validateUser(String userId) {
        if (!userClient.checkUserExists(userId)) {
            throw new WalletNotFoundException("User not found: " + userId);
        }
    }
}
