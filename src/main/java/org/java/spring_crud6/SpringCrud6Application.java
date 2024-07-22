package org.java.spring_crud6;

import java.util.Arrays;
import java.util.List;

import org.java.spring_crud6.db.pojo.Author;
import org.java.spring_crud6.db.pojo.Book;
import org.java.spring_crud6.db.pojo.Bookshelf;
import org.java.spring_crud6.db.serv.AuthorServ;
import org.java.spring_crud6.db.serv.BookServ;
import org.java.spring_crud6.db.serv.BookshelfServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud6Application implements CommandLineRunner {

	/**
	 * TODO: CRUD PER PARTI
	 * 
	 * FASE 1: DB
	 * Definire entita', repository e service
	 * per le seguenti entita':
	 * 
	 * Bookshelf
	 * - name : String
	 * - address : String
	 * 
	 * Book
	 * - title : String
	 * - author : Autore
	 * 
	 * Author
	 * - name : String
	 * - surname : String
	 * 
	 * Definire inoltre le realzioni tra le entita' e
	 * verificare in DbEaver se sono state create correttamente
	 * 
	 * --- CORREZIONE: 15:30 ---
	 * 
	 * FASE 2: CRUD on BOOK
	 * Definire controller che gestira' la tabella Book.
	 * Definire i metodi di tipo REST (da consumare con
	 * PostMan) per le seguenti operazioni:
	 * - leggere tutti gli elementi dalla tabella libro
	 * con relative relazioni
	 * - creare un nuovo libro da inserire in tabella
	 * - modificare un libro esistente
	 * - eliminare un libro esistente
	 * 
	 * N.B. 1:
	 * Attenzione ai loop nei json in risposta
	 * ai controller (@JsonIgnore)
	 * 
	 * N.B. 2:
	 * Attenzione alla creazione/modifica
	 * della tabella book in funzione delle proprie
	 * relazioni (e dei proprietari delle relazioni)
	 * 
	 */

	@Autowired
	private AuthorServ authorServ;

	@Autowired
	private BookServ bookServ;

	@Autowired
	private BookshelfServ bookshelfServ;

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud6Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Author author1 = new Author("Mario", "Rossi");
		Author author2 = new Author("Luigi", "Verdi");
		Author author3 = new Author("Giovanni", "Bianchi");

		authorServ.save(author1);
		authorServ.save(author2);
		authorServ.save(author3);

		System.out.println(author1);
		System.out.println("--------------------------");
		System.out.println(author2);
		System.out.println("--------------------------");
		System.out.println(author3);
		System.out.println("--------------------------");

		Book book1 = new Book("Il libro", author1);
		Book book2 = new Book("Il libro 2", author1);
		Book book3 = new Book("Il libro 3", author2);
		Book book4 = new Book("Il libro 4", author3);

		bookServ.save(book1);
		bookServ.save(book2);
		bookServ.save(book3);
		bookServ.save(book4);

		System.out.println(book1);
		System.out.println("--------------------------");
		System.out.println(book2);
		System.out.println("--------------------------");
		System.out.println(book3);
		System.out.println("--------------------------");
		System.out.println(book4);
		System.out.println("--------------------------");

		List<Book> bookList1 = Arrays.asList(book1, book2);
		List<Book> bookList2 = Arrays.asList(book3, book4);
		List<Book> bookList3 = Arrays.asList(book1, book3);

		Bookshelf bookshelf1 = new Bookshelf("Libreria 1", "Via Roma 1", bookList1);
		Bookshelf bookshelf2 = new Bookshelf("Libreria 2", "Via Roma 2", bookList2);
		Bookshelf bookshelf3 = new Bookshelf("Libreria 3", "Via Roma 3", bookList3);

		bookshelfServ.save(bookshelf1);
		bookshelfServ.save(bookshelf2);
		bookshelfServ.save(bookshelf3);

		System.out.println(bookshelf1);
		System.out.println("--------------------------");
		System.out.println(bookshelf2);
		System.out.println("--------------------------");
		System.out.println(bookshelf3);
		System.out.println("--------------------------");
	}

}
