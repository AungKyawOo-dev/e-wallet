package com.aungkyawoo.user_service.service.impl;

import com.aungkyawoo.user_service.dto.UserDto;
import com.aungkyawoo.user_service.dto.request.UserRequestDto;
import com.aungkyawoo.user_service.entity.UserEntity;
import com.aungkyawoo.user_service.exception.UserAlreadyExistsException;
import com.aungkyawoo.user_service.mapper.UserMapper;
import com.aungkyawoo.user_service.repository.UserRepository;
import com.aungkyawoo.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserRequestDto userRequestDto) {
        UserEntity user = UserMapper.mapToUserEntity(userRequestDto);
        Optional<UserEntity> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with the email: " + user.getEmail());
        }
        user = userRepository.save(user);
        log.info("User created with id: {}", user.getId());
        return UserMapper.mapToUserDto(user);
    }
}
