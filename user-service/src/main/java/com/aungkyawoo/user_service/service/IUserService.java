package com.aungkyawoo.user_service.service;

import com.aungkyawoo.user_service.dto.UserDto;
import com.aungkyawoo.user_service.dto.request.UserRequestDto;
import jakarta.validation.Valid;

public interface IUserService {
    UserDto createUser(UserRequestDto userRequestDto);

    UserDto getUser(Long id);

    UserDto updateUser(Long id, @Valid UserRequestDto userRequestDto);
}
