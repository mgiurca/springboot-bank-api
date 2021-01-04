package com.bankapi.service;

import com.bankapi.model.dto.TransactionHistoryDTO;
import com.bankapi.exception.UserNotFoundException;
import com.bankapi.model.Account;
import com.bankapi.model.Transaction;
import com.bankapi.model.dto.TransactionRequestDTO;
import com.bankapi.repository.AccountRepository;
import com.bankapi.repository.TransactionRepository;
import com.bankapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public Double doTransaction(TransactionRequestDTO transactionRequestDTO) {
        Account sourceAccount = userRepository.findById(transactionRequestDTO.getSourceUserId())
                .orElseThrow(UserNotFoundException::new).getAccount();
        Account detinationAccount = userRepository.findById(transactionRequestDTO.getDestinationUserId())
                .orElseThrow(UserNotFoundException::new).getAccount();

        //TODO: Implement validation for available amount
        sourceAccount.updateBalance(transactionRequestDTO.getAmount());
        detinationAccount.updateBalance(-1*transactionRequestDTO.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(detinationAccount);

        return transactionRepository.save(new Transaction(sourceAccount.getAccountNumber(),
                detinationAccount.getAccountNumber(), transactionRequestDTO.getAmount())).getAmount();

    }

    public TransactionHistoryDTO getHistory(String username) {
        Long accountId = userRepository.getUserByUserName(username).getAccount().getAccountNumber();
        return new TransactionHistoryDTO(transactionRepository.findAllByDestinationAccountId(accountId),
                transactionRepository.findAllBySourceAccountId(accountId));
    }
}
