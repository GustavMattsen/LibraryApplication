package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    List<AppUser> findByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<AppUser> findByDetailsId(Long detailsId);

    Optional<AppUser> findByDetailsEmailIgnoreCase(String email);
}
