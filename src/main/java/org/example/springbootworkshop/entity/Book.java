package org.example.springbootworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data                   // generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private int maxLoanDays;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private java.util.Set<Author> authors = new java.util.HashSet<>();
}

