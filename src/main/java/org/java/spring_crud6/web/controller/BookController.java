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
import org.java.spring_crud6.web.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/bookshelfs/{bookshelfId}")
    public ResponseEntity<List<Book>> getBooksByBookshelf(
            @PathVariable int bookshelfId) {

        Bookshelf bookshelf = bookshelfServ.findById(bookshelfId).get();
        List<Book> books = bookServ.findByBookshelf(bookshelf);

        return ResponseEntity.ok(books);
    }

    @PostMapping("")
    public ResponseEntity<Book> createBook(
            @RequestBody BookDto createBookDto) {

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

    @PatchMapping("{bookId}")
    public ResponseEntity<Book> updateBook(
            @PathVariable int bookId,
            @RequestBody BookDto bookDto) {

        System.out.println(bookDto);

        Optional<Book> optBook = bookServ.findById(bookId);

        if (optBook.isEmpty())
            return ResponseEntity.notFound().build();

        Book newBook = optBook.get();
        newBook.setTitle(bookDto.getTitle());

        Optional<Author> optNewBookAuthor = authorServ.findById(bookDto.getAuthorId());

        if (optNewBookAuthor.isEmpty())
            return ResponseEntity.notFound().build();

        Author newBookAuthor = optNewBookAuthor.get();
        newBook.setAuthor(newBookAuthor);

        bookServ.save(newBook);

        List<Bookshelf> newBookshelfs = new ArrayList<>();
        List<Bookshelf> allBookshelfs = bookshelfServ.findAll();
        for (Bookshelf bookshelf : allBookshelfs) {

            if (bookDto.getBookshelfIds()
                    .contains(bookshelf.getId())) {

                bookshelf.removeBook(newBook);
                bookshelf.addBook(newBook);
                bookshelfServ.save(bookshelf);
                newBookshelfs.add(bookshelf);
            } else {

                bookshelf.removeBook(newBook);
                bookshelfServ.save(bookshelf);
            }
        }

        newBook.setBookshelfs(newBookshelfs);

        return ResponseEntity.ok(newBook);
    }

    @DeleteMapping("{bookId}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable int bookId) {

        Optional<Book> optBook = bookServ.findById(bookId);

        if (optBook.isEmpty())
            return ResponseEntity.notFound().build();

        Book book = optBook.get();

        List<Bookshelf> bookshelfs = book.getBookshelfs();
        for (Bookshelf bookshelf : bookshelfs) {

            bookshelf.removeBook(book);
            bookshelfServ.save(bookshelf);
        }

        bookServ.delete(book);

        return ResponseEntity.ok().build();
    }
}
