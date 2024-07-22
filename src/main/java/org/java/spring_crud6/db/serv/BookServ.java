package org.java.spring_crud6.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud6.db.pojo.Book;
import org.java.spring_crud6.db.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServ {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> findAll() {

        return bookRepo.findAll();
    }

    public Optional<Book> findById(int id) {

        return bookRepo.findById(id);
    }

    public void save(Book book) {

        bookRepo.save(book);
    }

    public void delete(Book book) {

        bookRepo.delete(book);
    }
}
