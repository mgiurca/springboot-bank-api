package com.bankapi.service;

import com.bankapi.model.dto.AccountDTO;
import com.bankapi.model.Account;
import com.bankapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDTO save() {
        return new AccountDTO(accountRepository.save(new Account()));
    }

    public List<AccountDTO> getAllAccounts() {
        return this.accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }
}
