package org.example.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private LocalDate registrationDate;

    @OneToOne
    @JoinColumn
    private Details details;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookLoan> bookLoans = new ArrayList<>();

    // --- Relationship helpers ---
    public void addBookLoan(BookLoan loan) {
        bookLoans.add(loan);
        loan.setBorrower(this);
    }

    public void removeBookLoan(BookLoan loan) {
        bookLoans.remove(loan);
        loan.setBorrower(null);
    }

    //Convenience constructors

    // Skip ID, just username
    public AppUser(String username) {
        this.username = username;
    }

    // Skip ID, include username + registrationDate + details
    public AppUser(String username, LocalDate registrationDate, Details details) {
        this.username = username;
        this.registrationDate = registrationDate;
        this.details = details;
    }
}
