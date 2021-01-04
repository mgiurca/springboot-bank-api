package com.bankapi;


import com.bankapi.model.Account;
import com.bankapi.repository.AccountRepository;
import com.bankapi.repository.UserRepository;
import com.bankapi.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner{

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    Main(UserRepository userRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        User u = new User("test1","testemail", "test");
        Account account = new Account(400D);
        accountRepository.save(account);
        u.setAccount(account);
        userRepository.save(u);

        account = new Account(600D);
        accountRepository.save(account);
        u = new User("test2","test2email", "test2");
        u.setAccount(account);
        userRepository.save(u);

        u = new User("test3","test3email", "test3");
        userRepository.save(u);
    }
}
