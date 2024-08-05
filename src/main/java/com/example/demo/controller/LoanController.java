package com.example.demo.controller;

import com.example.demo.dto.LoanRequestDTO;
import com.example.demo.models.Loan;
import com.example.demo.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<String> requestLoan(@RequestBody LoanRequestDTO loanRequest) {
        loanService.requestLoan(loanRequest);
        return ResponseEntity.ok("Loan requested successfully");
    }

    @GetMapping  // Añadir la anotación @GetMapping aquí
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanService.getLoanById(id);
        return loan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
