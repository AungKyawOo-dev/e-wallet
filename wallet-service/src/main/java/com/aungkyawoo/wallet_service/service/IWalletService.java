package com.aungkyawoo.wallet_service.service;

import com.aungkyawoo.wallet_service.dto.WalletDto;
import com.aungkyawoo.wallet_service.dto.request.DepositRequestDto;

public interface IWalletService {
    WalletDto getWalletBalance(String userId);

    WalletDto initializeWallet(String userId);

    WalletDto deposit(DepositRequestDto depositRequestDto);
}
