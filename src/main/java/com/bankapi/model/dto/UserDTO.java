package com.bankapi.model.dto;

import com.bankapi.model.User;
import com.bankapi.model.dao.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Long accountNumber;

    public UserDTO(User user){
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.id = user.getId();
        this.accountNumber = user.getAccount() == null ? null : user.getAccount().getAccountNumber();
    }

    public UserDTO(UserDAO userDAO) {
        this.id = userDAO.getId();
        this.username = userDAO.getUsername();
        this.email = userDAO.getEmail();
        this.accountNumber = userDAO.getAccountNo();
    }
}
