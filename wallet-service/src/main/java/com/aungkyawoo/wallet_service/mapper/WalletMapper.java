package com.aungkyawoo.wallet_service.mapper;

import com.aungkyawoo.wallet_service.dto.WalletDto;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    public static WalletDto toWalletDto(com.aungkyawoo.wallet_service.entity.Wallet wallet) {
        return WalletDto.builder().userId(wallet.getUserId()).amount(wallet.getBalance()).currency(wallet.getCurrency()).build();
    }

}
