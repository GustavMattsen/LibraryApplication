package org.example.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnedDate; // null = not returned

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private AppUser borrower;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}

