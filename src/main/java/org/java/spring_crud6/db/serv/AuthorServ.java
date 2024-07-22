package org.java.spring_crud6.db.serv;

import java.util.List;
import java.util.Optional;

import org.java.spring_crud6.db.pojo.Author;
import org.java.spring_crud6.db.repo.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServ {

    @Autowired
    private AuthorRepo authorRepo;

    public List<Author> findAll() {

        return authorRepo.findAll();
    }

    public Optional<Author> findById(int id) {

        return authorRepo.findById(id);
    }

    public void save(Author author) {

        authorRepo.save(author);
    }

    public void delete(Author author) {

        authorRepo.delete(author);
    }
}
