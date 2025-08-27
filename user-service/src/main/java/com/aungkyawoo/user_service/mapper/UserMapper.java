package com.aungkyawoo.user_service.mapper;

import com.aungkyawoo.user_service.dto.UserDto;
import com.aungkyawoo.user_service.dto.request.UserRequestDto;
import com.aungkyawoo.user_service.entity.UserEntity;

public class UserMapper {

    public static UserEntity mapToUserEntity(UserRequestDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFullName(userDto.getFullName());
        userEntity.setPhoneNo(userDto.getPhoneNo());
        return userEntity;
    }

    public static UserDto mapToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setFullName(userEntity.getFullName());
        userDto.setPhoneNo(userEntity.getPhoneNo());
        userDto.setKycLevel(userEntity.getKycLevel());
        userDto.setStatus(userEntity.getStatus());
        return userDto;
    }
}
