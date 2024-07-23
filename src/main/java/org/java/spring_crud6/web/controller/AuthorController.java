package org.java.spring_crud6.web.controller;

import java.util.List;

import org.java.spring_crud6.db.pojo.Author;
import org.java.spring_crud6.db.serv.AuthorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/authors")
@CrossOrigin("http://localhost:5173")
public class AuthorController {

    @Autowired
    private AuthorServ authorServ;

    @GetMapping("")
    public ResponseEntity<List<Author>> getAllAuthors() {

        List<Author> authors = authorServ.findAll();

        return ResponseEntity.ok(authors);
    }

}
