package com.bankapi.controller;

import com.bankapi.model.dto.LoginRequestDTO;
import com.bankapi.service.UserService;
import com.bankapi.model.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginInfoDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.loginUser(loginInfoDTO));
    }
}
