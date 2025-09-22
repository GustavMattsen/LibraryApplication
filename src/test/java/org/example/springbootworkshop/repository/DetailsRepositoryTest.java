package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.Details;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
}
