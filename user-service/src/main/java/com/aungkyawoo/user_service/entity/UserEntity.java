package com.aungkyawoo.user_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * User Entity
 * Author : Aung Kyaw Oo
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Same as Auth userId

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(nullable = false)
    private String kycLevel = "BASIC";

    @Column(nullable = false)
    private String status = "ACTIVE";
}
