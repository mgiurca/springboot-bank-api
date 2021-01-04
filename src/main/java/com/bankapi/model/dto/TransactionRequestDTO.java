package com.bankapi.model.dto;

import lombok.Data;

@Data
public class TransactionRequestDTO {
    private Long sourceUserId;

    private Long destinationUserId;

    private Double amount;
}
