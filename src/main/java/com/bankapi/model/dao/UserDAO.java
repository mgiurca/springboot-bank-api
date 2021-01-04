package com.bankapi.model.dao;

import com.bankapi.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDAO {
    private Long id;
    private String username;
    private String email;
    private Long accountNo;
    private Double balance;

    public UserDAO(User user){
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.accountNo = user.getAccount() == null? null : user.getAccount().getAccountNumber();
    }
}
