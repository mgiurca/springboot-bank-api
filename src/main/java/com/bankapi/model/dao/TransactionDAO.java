package com.bankapi.model.dao;

import com.bankapi.model.Transaction;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionDAO {

    private Long sourceAccountId;

    private Long destinationAccountId;

    private Double amount;

    private String type;

    private Date timestamp;

    public TransactionDAO(Transaction transaction, String type){
        this.sourceAccountId = transaction.getSourceAccountId();
        this.destinationAccountId = transaction.getDestinationAccountId();
        this.amount = transaction.getAmount();
        this.timestamp = transaction.getTimestamp();
        this.type = type;
    }

}
