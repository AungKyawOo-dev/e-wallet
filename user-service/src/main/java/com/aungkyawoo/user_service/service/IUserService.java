package com.aungkyawoo.user_service.service;

import com.aungkyawoo.user_service.dto.UserDto;
import com.aungkyawoo.user_service.dto.request.UserRequestDto;
import jakarta.validation.Valid;

import java.util.UUID;

/**
 * IUserService interface
 * All the business logic will be here
 * Author: Aung Kyaw Oo
 */
public interface IUserService {
    UserDto createUser(UserRequestDto userRequestDto);

    UserDto getUser(String id);

    UserDto updateUser(String id, @Valid UserRequestDto userRequestDto);

    void deleteUser(String id);
}
