package com.aungkyawoo.wallet_service.repository;

import com.aungkyawoo.wallet_service.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByUserId(String userId);

    Optional<Wallet> findByUserIdAndCurrency(String userId, String currency);

}
