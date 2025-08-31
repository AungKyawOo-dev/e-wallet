package com.aungkyawoo.wallet_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Base Entity
 * Author : Aung Kyaw Oo
 */
@MappedSuperclass
@Data
@ToString
public class BaseEntity {

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(updatable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
