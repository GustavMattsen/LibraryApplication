package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbnIgnoreCase(String isbn);

    List<Book> findByTitleContaining(String title);

    List<Book> findByMaxLoanDaysLessThan(int days);
}

