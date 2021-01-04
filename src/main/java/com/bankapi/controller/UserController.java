package com.bankapi.controller;

import com.bankapi.service.UserService;
import com.bankapi.model.dto.RegisterRequestDTO;
import com.bankapi.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        return ResponseEntity.ok().body(this.userService.getUserByUsername(username));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<UserDTO> newUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok().body(userService.save(registerRequestDTO));
    }

    @PostMapping(path = "{username}/associateAccount/{accountId}")
    public ResponseEntity<UserDTO> associateAccount(@PathVariable String username, @PathVariable Long accountId) {
        return ResponseEntity.ok().body(this.userService.associateAccount(username, accountId));
    }

}
