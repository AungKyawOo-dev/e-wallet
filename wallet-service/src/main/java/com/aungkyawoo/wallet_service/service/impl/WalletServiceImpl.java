package com.aungkyawoo.wallet_service.service.impl;

import com.aungkyawoo.wallet_service.client.UserClient;
import com.aungkyawoo.wallet_service.constants.TransactionType;
import com.aungkyawoo.wallet_service.dto.WalletDto;
import com.aungkyawoo.wallet_service.dto.request.DepositRequestDto;
import com.aungkyawoo.wallet_service.entity.Transactions;
import com.aungkyawoo.wallet_service.entity.Wallet;
import com.aungkyawoo.wallet_service.exception.DuplicateTransactionException;
import com.aungkyawoo.wallet_service.exception.UserNotFoundException;
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
    public WalletDto getWalletBalance(String userId) {
        log.info("userId: {}", userId);
        validateUser(userId);
        Optional<Wallet> wallet = walletRepository.findByUserId(userId);
        if(wallet.isEmpty()) {
            return initializeWallet(userId);
        }

        return WalletMapper.toWalletDto(wallet.get());
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

    private void validateUser(String userId) {
        if (!userClient.checkUserExists(userId)) {
            throw new UserNotFoundException("User not found: " + userId);
        }
    }
}
