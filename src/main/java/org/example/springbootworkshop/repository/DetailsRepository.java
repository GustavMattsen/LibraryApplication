package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DetailsRepository extends JpaRepository<Details, Long> {

    Optional<Details> findByEmail(String email);

    List<Details> findByNameContains(String name);

    Optional<Details> findByNameIgnoreCase(String name);
}
