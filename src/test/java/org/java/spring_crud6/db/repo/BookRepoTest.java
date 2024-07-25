package org.java.spring_crud6.db.repo;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import org.java.spring_crud6.db.pojo.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepoTest {

    @Autowired
    private BookRepo bookRepo;

    private List<Book> allBooks;

    @BeforeEach
    void setUp() {
        allBooks = bookRepo.findAll();
    }

    @Test
    void testPluto() {

        List<Book> rightBooks = new ArrayList<>();
        rightBooks.add(allBooks.get(0));
        rightBooks.add(allBooks.get(1));

        List<Book> booksFBTSW = bookRepo.findByTitleStartingWith("s");
        assertAll(() -> {
            for (int i = 0; i < rightBooks.size(); i++) {
                assert rightBooks.get(i).getId() == booksFBTSW.get(i).getId();
            }
        });

        List<Book> booksPluto = bookRepo.pluto("s");

        // rightBooks and books must be equals in all elements
        assertAll(() -> {
            for (int i = 0; i < rightBooks.size(); i++) {
                assert rightBooks.get(i).getId() == booksPluto.get(i).getId();
            }
        });

        List<Book> booksPluto2 = bookRepo.pluto2("s");

        // rightBooks and books must be equals in all elements
        assertAll(() -> {
            for (int i = 0; i < rightBooks.size(); i++) {
                assert rightBooks.get(i).getId() == booksPluto2.get(i).getId();
            }
        });
    }
}
