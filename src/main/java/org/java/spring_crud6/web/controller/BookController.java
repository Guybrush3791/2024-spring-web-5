package org.java.spring_crud6.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.java.spring_crud6.db.pojo.Author;
import org.java.spring_crud6.db.pojo.Book;
import org.java.spring_crud6.db.pojo.Bookshelf;
import org.java.spring_crud6.db.serv.AuthorServ;
import org.java.spring_crud6.db.serv.BookServ;
import org.java.spring_crud6.db.serv.BookshelfServ;
import org.java.spring_crud6.web.dto.CreateBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin("http://localhost:5173")
public class BookController {

    @Autowired
    private BookServ bookServ;

    @Autowired
    private AuthorServ authorServ;

    @Autowired
    private BookshelfServ bookshelfServ;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {

        List<Book> books = bookServ.findAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(
            @RequestBody CreateBookDto createBookDto) {

        System.out.println(createBookDto);

        Book newBook = new Book(createBookDto);
        Optional<Author> optNewBookAuthor = authorServ.findById(createBookDto.getAuthorId());

        if (optNewBookAuthor.isEmpty())
            return ResponseEntity.notFound().build();

        Author newBookAuthor = optNewBookAuthor.get();
        newBook.setAuthor(newBookAuthor);

        bookServ.save(newBook);

        List<Bookshelf> bookshelves = new ArrayList<>();
        for (int bookshelfId : createBookDto.getBookshelfIds()) {

            Optional<Bookshelf> bookShelf = bookshelfServ.findById(bookshelfId);

            if (bookShelf.isEmpty())
                continue;

            Bookshelf bookshelf = bookShelf.get();
            bookshelf.addBook(newBook);

            bookshelfServ.save(bookshelf);

            bookshelves.add(bookshelf);
        }

        newBook.setBookshelfs(bookshelves);

        return ResponseEntity.ok(newBook);
    }
}
