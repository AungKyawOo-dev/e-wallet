package com.aungkyawoo.user_service.service.impl;

import com.aungkyawoo.user_service.dto.UserDto;
import com.aungkyawoo.user_service.dto.request.UserRequestDto;
import com.aungkyawoo.user_service.entity.UserEntity;
import com.aungkyawoo.user_service.exception.ResourceNotFoundException;
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

    @Override
    public UserDto getUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", String.valueOf(id)));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(()
                ->new ResourceNotFoundException("User", "Email", userRequestDto.getEmail()));
        user.setFullName(userRequestDto.getFullName());
        user.setPhoneNo(userRequestDto.getPhoneNo());
        user = userRepository.save(user);
        log.info("User updated with id: {}", id);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", String.valueOf(id)));
        userRepository.delete(user);
        log.info("User deleted with id: {}", id);
    }
}
