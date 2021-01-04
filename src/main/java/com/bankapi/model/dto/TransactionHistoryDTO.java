package com.bankapi.model.dto;

import com.bankapi.model.Transaction;
import com.bankapi.model.dao.TransactionDAO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionHistoryDTO {
    List<TransactionDAO> transactions = new ArrayList<>();

    public TransactionHistoryDTO(List<Transaction> outcomeTransactions, List<Transaction> incomeTransactions) {

        outcomeTransactions
                .forEach(outcomeTransaction -> transactions.add(new TransactionDAO(outcomeTransaction, "outcome")));
        incomeTransactions
                .forEach(incomeTransaction -> transactions.add(new TransactionDAO(incomeTransaction, "income")));
    }
}
