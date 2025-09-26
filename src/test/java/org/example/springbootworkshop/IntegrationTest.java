package org.example.springbootworkshop;

import org.example.springbootworkshop.entity.*;
import org.example.springbootworkshop.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class IntegrationTest {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Test
    void testEndToEndWorkflow() {
        // Create Author
        Author author = new Author("Jane", "Austen");
        authorRepository.save(author);

        // Create Book and link to Author
        Book book = new Book("111-222", "Pride and Prejudice", 14);
        book.addAuthor(author);
        bookRepository.save(book);

        // Create User
        AppUser user = new AppUser("john_doe");
        appUserRepository.save(user);

        // Create Loan and add to user (borrower will be set by addBookLoan)
        BookLoan loan = new BookLoan();
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14));
        loan.setBook(book);

        user.addBookLoan(loan);
        appUserRepository.save(user);

        // Check repository counts
        assertThat(authorRepository.count()).isEqualTo(1);
        assertThat(bookRepository.count()).isEqualTo(1);
        assertThat(appUserRepository.count()).isEqualTo(1);
        assertThat(bookLoanRepository.count()).isEqualTo(1);

        // Check bidirectional relationships
        AppUser savedUser = appUserRepository.findAllWithLoans().get(0);
        assertThat(savedUser.getBookLoans()).hasSize(1);
        assertThat(savedUser.getBookLoans().get(0).getBook().getTitle())
                .isEqualTo("Pride and Prejudice");
    }
}