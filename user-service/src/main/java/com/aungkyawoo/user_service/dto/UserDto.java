package com.aungkyawoo.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

/**
 * User Dto
 * Author : Aung Kyaw Oo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String id;

    private String email;

    private String fullName;

    private String phoneNo;

    private String kycLevel;

    private String status;
}
