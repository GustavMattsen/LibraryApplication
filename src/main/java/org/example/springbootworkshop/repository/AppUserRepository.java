package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    List<AppUser> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<AppUser> findByDetails_Id(Long detailsId);

    Optional<AppUser> findByDetails_EmailIgnoreCase(String email);

    @Query("SELECT u FROM AppUser u LEFT JOIN FETCH u.bookLoans")
    List<AppUser> findAllWithLoans();
}
