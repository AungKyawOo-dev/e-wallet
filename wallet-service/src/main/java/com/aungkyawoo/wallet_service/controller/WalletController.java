package com.aungkyawoo.wallet_service.controller;

import com.aungkyawoo.wallet_service.constants.WalletConstants;
import com.aungkyawoo.wallet_service.dto.WalletDto;
import com.aungkyawoo.wallet_service.dto.request.DepositRequestDto;
import com.aungkyawoo.wallet_service.dto.request.WithdrawRequestDto;
import com.aungkyawoo.wallet_service.dto.response.ResponseDto;
import com.aungkyawoo.wallet_service.service.IWalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/wallets")
public class WalletController {

    private final IWalletService walletService;

    @PostMapping("/{userId}/init")
    public ResponseEntity<ResponseDto> initWallet(@PathVariable String userId, @RequestParam String currency) {
        log.info("wallet innit with userId: {}", userId);
        WalletDto walletDto = walletService.initializeWallet(userId);
        return ResponseEntity.ok(new ResponseDto(WalletConstants.STATUS_201, WalletConstants.MESSAGE_201, walletDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto> getWalletBalance(@PathVariable String userId, @RequestParam String currency) {
        log.info("userId: {}", userId);
        WalletDto walletDto = walletService.getWalletBalance(userId, currency);
        return ResponseEntity.ok(new ResponseDto(WalletConstants.STATUS_200, WalletConstants.MESSAGE_200, walletDto));
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseDto> deposit(@RequestBody @Valid DepositRequestDto depositRequestDto) {
        log.info("deposit with userId: {}", depositRequestDto.getUserId());
        WalletDto walletDto = walletService.deposit(depositRequestDto);
        return ResponseEntity.ok(new ResponseDto(WalletConstants.STATUS_200, WalletConstants.MESSAGE_200, walletDto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseDto> withdraw(@RequestBody @Valid WithdrawRequestDto withdrawRequestDto) {
        log.info("withdraw with userId: {}", withdrawRequestDto.getUserId());
        WalletDto walletDto = walletService.widthdraw(withdrawRequestDto);
        return ResponseEntity.ok(new ResponseDto(WalletConstants.STATUS_200, WalletConstants.MESSAGE_200, walletDto));
    }

}
