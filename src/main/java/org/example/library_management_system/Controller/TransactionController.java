package org.example.library_management_system.Controller;

import jakarta.validation.Valid;
import org.example.library_management_system.DTO.TransactionRequest;
import org.example.library_management_system.Exceptions.TransactionException;
import org.example.library_management_system.Model.Transactions;
import org.example.library_management_system.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public ResponseEntity<Transactions> issueBook(@RequestBody @Valid TransactionRequest request){
        Transactions createdTransaction = transactionService.issueBook(request);
        return new ResponseEntity<>(createdTransaction, HttpStatus.OK);
    }
}
