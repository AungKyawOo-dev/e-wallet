CREATE TABLE wallets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id CHAR(36) NOT NULL,
    currency VARCHAR(10) NOT NULL, -- e.g., USD, EUR, JPY
    balance DECIMAL(19,4) NOT NULL DEFAULT 0.0000,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_user_currency (user_id, currency)
);


CREATE TABLE wallet_transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    from_user_id CHAR(36) NOT NULL,
    to_user_id CHAR(36) NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    type ENUM('DEPOSIT', 'WITHDRAW', 'TRANSFER_IN', 'TRANSFER_OUT') NOT NULL,
    reference VARCHAR(36),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_from_user_id (from_user_id),
    INDEX idx_to_user_id (to_user_id),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
