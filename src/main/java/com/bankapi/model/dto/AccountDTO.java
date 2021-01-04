package com.bankapi.model.dto;

import com.bankapi.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class AccountDTO {

    private Long accountNumber;
    private Set<UserDTO> userDTOSet;

    public AccountDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.userDTOSet = account.getUserSet().stream().map(UserDTO::new).collect(Collectors.toSet());
    }
}
