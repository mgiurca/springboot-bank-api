package com.bankapi.service;

import com.bankapi.exception.AccountNotFoundException;
import com.bankapi.exception.InvalidPasswordException;
import com.bankapi.exception.UserNotFoundException;
import com.bankapi.model.Account;
import com.bankapi.model.User;
import com.bankapi.model.dao.UserDAO;
import com.bankapi.model.dto.LoginRequestDTO;
import com.bankapi.model.dto.RegisterRequestDTO;
import com.bankapi.model.dto.UserDTO;
import com.bankapi.repository.AccountRepository;
import com.bankapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;
    AccountRepository accountRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public UserDTO getUserByUsername(String username) {
        UserDAO userDAO = new UserDAO(Optional.ofNullable(userRepository.getUserByUserName(username))
                .orElseThrow(UserNotFoundException::new));
        return new UserDTO(userDAO);
    }

    public UserDTO save(RegisterRequestDTO registerRequestDTO){
        User newUser = new User(registerRequestDTO);
        newUser.setPassword(registerRequestDTO.getPassword());
        return new UserDTO(userRepository.save(newUser));
    }

    public UserDTO loginUser(LoginRequestDTO loginInfoDTO) {
        UserDAO userDAO = new UserDAO(Optional.ofNullable(userRepository.getUserByUserName(loginInfoDTO.getUsername()))
                .orElseThrow(UserNotFoundException::new));
        //TODO: Store hashed password and validate with user input
        //TODO: Solve for validation inside repository not in service
        if(!userRepository.getOne(userDAO.getId()).validatePassword(loginInfoDTO.getPassword())) {
            throw new InvalidPasswordException();
        }

        return new UserDTO(userDAO);
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO associateAccount(String username, Long accountId) {
        User user = Optional.ofNullable(userRepository.getUserByUserName(username))
                .orElseThrow(UserNotFoundException::new);
        Account account = Optional.ofNullable(accountRepository.getByAccountNumber(accountId))
                .orElseThrow(AccountNotFoundException::new);

        user.setAccount(account);

        return new UserDTO(this.userRepository.save(user));
    }
}
