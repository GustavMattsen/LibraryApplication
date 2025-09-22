package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.Details;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class DetailsRepositoryTest {

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    void testSaveAndFindByEmail() {
        Details details = new Details(null, "unique@example.com", "Unique Name");
        detailsRepository.save(details);

        Optional<Details> found = detailsRepository.findByEmail("unique@example.com");

        assertTrue(found.isPresent());
        assertThat(found.get().getName()).isEqualTo("Unique Name");
    }

    @Test
    void testFindByNameContains() {
        Details details1 = new Details(null, "a@example.com", "Alice Wonderland");
        Details details2 = new Details(null, "b@example.com", "Bob Builder");
        detailsRepository.save(details1);
        detailsRepository.save(details2);

        List<Details> found = detailsRepository.findByNameContains("Ali");

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getEmail()).isEqualTo("a@example.com");
    }

    @Test
    void testFindByNameIgnoreCase() {
        Details details = new Details(null, "case@example.com", "CaseTest");
        detailsRepository.save(details);

        Optional<Details> found = detailsRepository.findByNameIgnoreCase("casetest");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("case@example.com");
    }
}
