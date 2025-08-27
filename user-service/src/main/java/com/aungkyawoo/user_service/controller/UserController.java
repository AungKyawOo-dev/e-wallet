package com.aungkyawoo.user_service.controller;


import com.aungkyawoo.user_service.constants.UserConstants;
import com.aungkyawoo.user_service.dto.UserDto;
import com.aungkyawoo.user_service.dto.request.UserRequestDto;
import com.aungkyawoo.user_service.dto.response.ResponseDto;
import com.aungkyawoo.user_service.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/greeting")
    public ResponseEntity<String>    greeting() {
       return ResponseEntity.ok("Hello from User Service");
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        log.info("Creating user: {}", userRequestDto);
        UserDto userDto = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201, userDto));
    }

}
