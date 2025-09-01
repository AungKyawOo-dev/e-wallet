package com.aungkyawoo.wallet_service.repository;

import com.aungkyawoo.wallet_service.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    boolean existsByReference(String reference);
}
