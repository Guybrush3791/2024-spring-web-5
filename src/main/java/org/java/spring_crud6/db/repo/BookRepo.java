package org.java.spring_crud6.db.repo;

import java.util.List;

import org.java.spring_crud6.db.pojo.Book;
import org.java.spring_crud6.db.pojo.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findByBookshelfs(Bookshelf bookshelfs);

    // get all books that start with a certain character
    List<Book> findByTitleStartingWith(String startCharacter);

    // JPQL
    @Query("""
                SELECT b
                FROM Book b
                WHERE b.title LIKE ?1%
            """)
    List<Book> pluto(String startCharacter);

    // Native Query
    // ALL BOOKS THAT START WITH A CERTAIN CHARACTER
    @Query(value = """
                SELECT *
                FROM book
                WHERE title LIKE 's%'
            """, nativeQuery = true)
    List<Book> pluto2(String startCharacter);
}

/**
 * SELECT *
 * FROM book
 * WHERE title LIKE '${startCharacter}%';
 */