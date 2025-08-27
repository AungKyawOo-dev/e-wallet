package com.aungkyawoo.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
