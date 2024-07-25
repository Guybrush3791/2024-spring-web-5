package org.java.spring_crud6.db.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

@SpringBootTest
public class BookshelfTest {

    private Bookshelf bookshelf;

    @BeforeEach
    public void init() {
        System.out.println("Initializing...");

        bookshelf = new Bookshelf();
    }

    @Test
    public void testSetNameException() throws Exception {

        assertThrowsExactly(Exception.class, () -> {
            bookshelf.setName(null);
        });
        assertThrowsExactly(Exception.class, () -> {
            bookshelf.setName("");
        });
        assertThrowsExactly(Exception.class, () -> {
            bookshelf.setName("Guybrush Threepwood, the Bloodnose Pirate;Guybrush Threepwood, th");
        });
    }

    @Test
    public void testSetName() throws Exception {

        final String defName1 = "Guybrush";
        final String defName2 = "Elaine";

        bookshelf.setName(defName1);
        assertEquals(bookshelf.getName(), defName1);

        bookshelf.setName(defName2);
        assertEquals(bookshelf.getName(), defName2);
    }

    @Test
    public void testSetAddress() {
        System.out.println("Testing setAddress...");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down...");
    }

    @AfterAll
    public static void destroy() {
        System.out.println("Destroying...");
    }
}
