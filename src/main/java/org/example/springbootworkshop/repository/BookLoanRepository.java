package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

    List<BookLoan> findByBorrower_Id(Long borrowerId);

    List<BookLoan> findByBook_Id(Long bookId);

    List<BookLoan> findByReturnedDateIsNull();

    List<BookLoan> findByDueDateBeforeAndReturnedDateIsNull(LocalDate date); // overdue

    List<BookLoan> findByLoanDateBetween(LocalDate start, LocalDate end);
}

