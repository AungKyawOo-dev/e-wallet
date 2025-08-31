package com.aungkyawoo.wallet_service.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User Dto
 * Author : Aung Kyaw Oo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;

    private String email;

    private String fullName;

    private String phoneNo;

    private String kycLevel;

    private String status;
}
