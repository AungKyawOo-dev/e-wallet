package com.aungkyawoo.wallet_service.controller;

import com.aungkyawoo.wallet_service.constants.WalletConstants;
import com.aungkyawoo.wallet_service.dto.WalletDto;
import com.aungkyawoo.wallet_service.dto.response.ResponseDto;
import com.aungkyawoo.wallet_service.service.IWalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallets")
public class WalletController {

    private final IWalletService walletService;

    @PostMapping("/{userId}/init")
    public ResponseEntity<ResponseDto> initWallet(@PathVariable Long userId) {
        log.info("wallet innit with userId: {}", userId);
        WalletDto walletDto = walletService.initializeWallet(userId);
        return ResponseEntity.ok(new ResponseDto(WalletConstants.STATUS_200, WalletConstants.MESSAGE_200, walletDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto> getWalletBalance(@PathVariable Long userId) {
        log.info("userId: {}", userId);
        WalletDto walletDto = walletService.getWalletBalance(userId);
        return ResponseEntity.ok(new ResponseDto(WalletConstants.STATUS_200, WalletConstants.MESSAGE_200, walletDto));

    }

}
