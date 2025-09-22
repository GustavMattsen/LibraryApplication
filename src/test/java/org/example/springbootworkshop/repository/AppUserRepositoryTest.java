package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.AppUser;
import org.example.springbootworkshop.entity.Details;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;


    @Test
    public void testSaveUserWithDetailsAndFindByUsername() {
        Details details = new Details();
        details.setEmail("sven@svensson.com");
        details.setName("Sven Svensson");
        detailsRepository.save(details);

        AppUser user = new AppUser();
        user.setUsername("Sven123");
        user.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
        user.setDetails(details);
        appUserRepository.save(user);

        Optional<AppUser> found = appUserRepository.findByUsername("Sven123");
        assertTrue(found.isPresent());
        assertEquals("Sven123", found.get().getUsername());
        assertEquals("sven@svensson.com", found.get().getDetails().getEmail());
    }

    @Test
    public void testFindByDetailsId() {
        Details details = new Details();
        details.setEmail("idtest@example.com");
        details.setName("ID Test");
        detailsRepository.save(details);

        AppUser user = new AppUser();
        user.setUsername("idUser");
        user.setRegistrationDate(LocalDate.now());
        user.setDetails(details);
        appUserRepository.save(user);

        Long detailsId = details.getId();
        Optional<AppUser> found = appUserRepository.findByDetails_Id(detailsId);
        assertTrue(found.isPresent());
        assertEquals("idUser", found.get().getUsername());
    }

    @Test
    void testFindByDetailsEmailIgnoreCase() {
        Details details = new Details(null, "example@example.com", "Case Test");
        Details savedDetails = detailsRepository.save(details);

        AppUser user = new AppUser(null, "testUser", LocalDate.now(), savedDetails);
        appUserRepository.save(user);

            Optional<AppUser> found = appUserRepository.findByDetails_EmailIgnoreCase("EXAMPLE@EXAMPLE.COM");

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testUser");
    }

    @Test
    void testFindByRegistrationDateBetween() {
        Details details = new Details(null, "test@example.com", "Range Test");
        detailsRepository.save(details);

        AppUser user = new AppUser(null, "rangeUser", LocalDate.of(2024, 1, 15), details);
        appUserRepository.save(user);

        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 31);

        var foundUsers = appUserRepository.findByRegistrationDateBetween(start, end);

        assertThat(foundUsers).isNotEmpty();
        assertThat(foundUsers.get(0).getUsername()).isEqualTo("rangeUser");
    }
}
