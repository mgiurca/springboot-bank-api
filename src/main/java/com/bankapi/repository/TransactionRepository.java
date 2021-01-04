package com.bankapi.repository;

import com.bankapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySourceAccountId(Long sourceAccountId);
    List<Transaction> findAllByDestinationAccountId(Long destinationAccountId);
}
