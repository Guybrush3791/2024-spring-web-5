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

    // name : String : 64 : not null
    // address : String : 128 : not null
    // books : List<Book> : not null

    // setName : void : Exception : name != null && name.length() <= 64
    // setAddress : void : Exception : address != null && address.length() <= 128
    // removeBook : void : void : book != null

    // ------------------------------------------------------------------------------

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

    public Bookshelf(String name, String address, List<Book> books) throws Exception {

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

    public void setName(String name) throws Exception {

        if (name == null || name.isEmpty())
            throw new Exception("Name is empty");

        if (name.length() > 64)
            throw new Exception("Name is too long");

        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {

        if (name == null || name.isEmpty())
            throw new Exception("Address is empty");

        if (name.length() > 128)
            throw new Exception("Address is too long");

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

    public void removeBook(Book book) {

        for (int i = 0; i < books.size(); i++) {

            Book b = books.get(i);

            if (b.getId() == book.getId()) {
                books.remove(i);
                return;
            }
        }
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
