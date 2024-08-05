package com.example.demo.service;

import com.example.demo.dto.LoanRequestDTO;
import com.example.demo.models.Loan;
import com.example.demo.models.Resource;
import com.example.demo.models.Users;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {


    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private UserRepository userRepository;

    public void requestLoan(LoanRequestDTO loanRequest) {
        // Verificar si el recurso existe
        Resource resource = resourceRepository.findById(loanRequest.getResourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        // Verificar si el usuario existe
        Users user = userRepository.findById(loanRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Crear un nuevo préstamo
        Loan loan = new Loan();
        loan.setResource(resource);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());

        // Guardar el préstamo en la base de datos
        loanRepository.save(loan);

        // Opcional: Actualizar el estado del recurso (si es necesario)
        // resource.setAvailable(false); // Suponiendo que tienes un campo de disponibilidad
        // resourceRepository.save(resource);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }
}
