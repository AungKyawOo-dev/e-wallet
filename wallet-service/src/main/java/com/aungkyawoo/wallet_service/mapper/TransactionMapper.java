package com.aungkyawoo.wallet_service.mapper;

import com.aungkyawoo.wallet_service.dto.TransactionDto;
import com.aungkyawoo.wallet_service.entity.Transactions;

public class TransactionMapper {

    public static TransactionDto toTransactionDto(Transactions transactions) {
        return TransactionDto.builder()
                .fromUserId(transactions.getFromUserId())
                .toUserId(transactions.getToUserId())
                .amount(transactions.getAmount())
                .type(transactions.getType())
                .reference(transactions.getReference())
                .build();
    }
}
