package com.aungkyawoo.user_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequestDto {
    @Email
    @NotBlank
    String email;
    @Size(max = 100)
    String fullName;
    @Size(max = 20)
    String phoneNo;
    String kycLevel;
    String status;
}
