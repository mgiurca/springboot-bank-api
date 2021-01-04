package com.bankapi.model;

import com.bankapi.model.dto.RegisterRequestDTO;
import lombok.*;

import javax.persistence.*;


@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String email;

    //TODO: replace with hash
    @Setter
    private String password;

    @ManyToOne
    @Getter @Setter
    private Account account;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(RegisterRequestDTO registerRequestDTO) {
        this.userName = registerRequestDTO.getUsername();
        this.email = registerRequestDTO.getEmail();
    }

    public boolean validatePassword(String password) {
        //TODO: hash password and validate with this.password hash
        return this.password.equals(password);
    }
}
