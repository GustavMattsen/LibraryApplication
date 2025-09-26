package org.example.springbootworkshop.repository;

import org.example.springbootworkshop.entity.Author;
import org.example.springbootworkshop.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testFindByFirstName() {
        Author author = new Author("John", "Doe");
        authorRepository.save(author);

        List<Author> result = authorRepository.findByFirstName("John");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getLastName()).isEqualTo("Doe");
    }
}
