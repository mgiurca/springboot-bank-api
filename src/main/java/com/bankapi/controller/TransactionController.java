package com.bankapi.controller;

import com.bankapi.model.dto.TransactionHistoryDTO;
import com.bankapi.service.TransactionService;
import com.bankapi.model.dto.TransactionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Double> doTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO){
        return ResponseEntity.accepted().body(transactionService.doTransaction(transactionRequestDTO));
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<TransactionHistoryDTO> getTransactionHistory(@PathVariable String username) {
        return ResponseEntity.ok().body(transactionService.getHistory(username));
    }
}
