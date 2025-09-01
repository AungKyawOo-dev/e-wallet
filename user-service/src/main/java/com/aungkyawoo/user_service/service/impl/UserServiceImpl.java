package com.aungkyawoo.user_service.service.impl;

import com.aungkyawoo.user_service.client.WalletClient;
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
import java.util.UUID;

/**
 * User Service Implementation
 * All the business logic will be handled here
 * Author : Aung Kyaw Oo
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    // Injecting UserRepository
    private final UserRepository userRepository;

    private final WalletClient walletClient;

    /**
     * Create User
     * @param userRequestDto UserRequestDto
     * @return UserDto
     */
    @Override
    public UserDto createUser(UserRequestDto userRequestDto) {
        UserEntity user = UserMapper.mapToUserEntity(userRequestDto);
        Optional<UserEntity> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("Customer already registered with the email: " + user.getEmail());
        }
        user = userRepository.save(user);
        log.info("User created with id: {}", user.getId());
        walletClient.initWallet(user.getId());
        return UserMapper.mapToUserDto(user);
    }

    /**
     * Get User
     * @param id Long
     * @return UserDto
     */
    @Override
    public UserDto getUser(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", id));
        return UserMapper.mapToUserDto(user);
    }

    /**
     * Update User
     * @param id Long
     * @param userRequestDto UserRequestDto
     * @return UserDto
     */
    @Override
    public UserDto updateUser(String id, UserRequestDto userRequestDto) {
        UserEntity user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(()
                ->new ResourceNotFoundException("User", "Email", userRequestDto.getEmail()));
        user.setFullName(userRequestDto.getFullName());
        user.setPhoneNo(userRequestDto.getPhoneNo());
        user = userRepository.save(user);
        log.info("User updated with id: {}", id);
        return UserMapper.mapToUserDto(user);
    }

    /**
     * Delete User
     * @param id Long
     */
    @Override
    public void deleteUser(String id) {
        UserEntity user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "id", String.valueOf(id)));
        userRepository.delete(user);
        log.info("User deleted with id: {}", id);
    }
}
