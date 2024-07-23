package org.java.spring_crud6.db.pojo;

import java.util.List;

import org.java.spring_crud6.web.dto.BookDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 128)
    private String title;

    @ManyToOne
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<Bookshelf> bookshelfs;

    public Book() {
    }

    public Book(String title, Author author) {

        setTitle(title);
        setAuthor(author);
    }

    public Book(BookDto createBookDto) {

        setTitle(createBookDto.getTitle());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public Author getAuthor() {

        return author;
    }

    public void setAuthor(Author author) {

        this.author = author;
    }

    public List<Bookshelf> getBookshelfs() {

        return bookshelfs;
    }

    public void setBookshelfs(List<Bookshelf> bookshelfs) {

        this.bookshelfs = bookshelfs;
    }

    @Override
    public String toString() {

        return "Book {\n" +
                "id=" + id + ",\n" +
                "title='" + title + '\'' + "\n" +
                '}';
    }

}
