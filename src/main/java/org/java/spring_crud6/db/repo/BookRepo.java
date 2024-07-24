package org.java.spring_crud6.db.repo;

import java.util.List;

import org.java.spring_crud6.db.pojo.Book;
import org.java.spring_crud6.db.pojo.Bookshelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findByBookshelfs(Bookshelf bookshelfs);
}
