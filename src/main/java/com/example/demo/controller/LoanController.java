package com.example.demo.controller;

import com.example.demo.dto.LoanRequestDTO;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<String> requestLoan(@RequestBody LoanRequestDTO loanRequest) {
        loanService.requestLoan(loanRequest);
        return ResponseEntity.ok("Loan requested successfully");
    }
}
