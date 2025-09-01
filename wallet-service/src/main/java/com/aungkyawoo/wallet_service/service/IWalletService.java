package com.aungkyawoo.wallet_service.service;

import com.aungkyawoo.wallet_service.dto.WalletDto;
import com.aungkyawoo.wallet_service.dto.request.DepositRequestDto;
import com.aungkyawoo.wallet_service.dto.request.WithdrawRequestDto;
import jakarta.validation.Valid;

public interface IWalletService {
    WalletDto getWalletBalance(String userId, String currency);

    WalletDto initializeWallet(String userId);

    WalletDto deposit(DepositRequestDto depositRequestDto);

    WalletDto widthdraw(WithdrawRequestDto withdrawRequestDto);
}
