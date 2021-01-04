package com.bankapi.repository;

import com.bankapi.model.Account;
import com.bankapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account getByAccountNumber(Long accountNumber);
    Account getAccountByUserSetContains(User user);
}
