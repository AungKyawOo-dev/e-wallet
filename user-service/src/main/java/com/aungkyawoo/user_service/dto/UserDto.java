package com.aungkyawoo.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
