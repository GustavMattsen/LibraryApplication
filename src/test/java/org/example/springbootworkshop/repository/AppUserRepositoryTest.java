package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.AppUser;
import org.example.springbootworkshop.entity.Details;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    void testSaveAppUser() {
        //Create and save Details first
        Details details = new Details();
        details.setEmail("sture@example.com");
        details.setName("Sture");
        Details savedDetails = detailsRepository.save(details);

        //Create AppUser and link to the saved details
        AppUser appUser = new AppUser();
        appUser.setUsername("sture123");
        appUser.setRegistrationDate(LocalDate.now());
        appUser.setDetails(savedDetails);

        //Save AppUser
        AppUser savedAppUser = appUserRepository.save(appUser);

        //Assertions to verify save worked
        assertThat(savedAppUser.getId()).isNotNull();
        assertThat(savedAppUser.getUsername()).isEqualTo("sture123");
        assertThat(savedAppUser.getDetails().getEmail()).isEqualTo("sture@example.com");
    }
}
