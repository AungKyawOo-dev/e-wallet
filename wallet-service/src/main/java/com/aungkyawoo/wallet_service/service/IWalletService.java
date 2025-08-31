package com.aungkyawoo.wallet_service.service;

import com.aungkyawoo.wallet_service.dto.WalletDto;

public interface IWalletService {
    WalletDto getWalletBalance(Long userId);

    WalletDto initializeWallet(Long userId);
}
