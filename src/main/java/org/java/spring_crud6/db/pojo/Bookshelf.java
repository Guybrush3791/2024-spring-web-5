package org.java.spring_crud6.db.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false, length = 128)
    private String address;

    @JsonIgnore
    @ManyToMany
    private List<Book> books;

    public Bookshelf() {
    }

    public Bookshelf(String name, String address, List<Book> books) {

        setName(name);
        setAddress(address);
        setBooks(books);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {

        books.add(book);
    }

    @Override
    public String toString() {

        return "Bookshelf {\n" +
                "\tid: " + id + ",\n" +
                "\tname: " + name + ",\n" +
                "\taddress: " + address + "\n" +
                "}";
    }
}
