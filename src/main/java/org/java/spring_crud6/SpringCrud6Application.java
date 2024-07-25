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
	 */

	/**
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
	 * --- CORREZIONE: 17:15 ---
	 */

	/**
	 * 
	 * FASE 3: FRONTEND
	 * Definire in nuovo progetto Vue.js una pagina
	 * che consumi i servizi REST definiti in precedenza,
	 * dando la possibilita' all'utente di:
	 * - vedere tutti i libri con autore e librerie associate
	 * - creare un nuovo libro
	 * - modificare un libro esistente
	 * - eliminare un libro esistente
	 * 
	 * --- CORREZIONE: 12:00 ---
	 * 
	 */

	/**
	 * 
	 * FASE 4: CRUD on BOOKSHELF
	 * Introdurre i vincoli di integrita' all'interno della classe
	 * Bookshelf.
	 * 
	 * Definire all'interno del controller dedicato a Bookshelf
	 * un metodo per ottenere la lista di tutte le librerie con
	 * tutte le realzioni associate (libri e relativi autori).
	 * 
	 * Dopo aver testato l'API attraverso PostMan, generare pagina
	 * dedicata in progetto front-end per la visualizzazione di
	 * tutti i dati (librerie con relativi libri e autori).
	 * 
	 * --- CORREZIONE: 11:30 ---
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
	public void run(String... args) {

		try {
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

			// 10 fake book with real titles
			Book book1 = new Book("Signore degli anelli", author1);
			Book book2 = new Book("Silmarillion", author2);
			Book book3 = new Book("Nome della rosa", author3);
			Book book4 = new Book("Vecchio e il mare", author1);
			Book book5 = new Book("Gattopardo", author2);
			Book book6 = new Book("Deserto dei tartari", author3);
			Book book7 = new Book("Barone rampante", author1);
			Book book8 = new Book("Visconte dimezzato", author2);
			Book book9 = new Book("Cavaliere inesistente", author3);
			Book book10 = new Book("Pendolo di Foucault", author1);

			bookServ.save(book1);
			bookServ.save(book2);
			bookServ.save(book3);
			bookServ.save(book4);
			bookServ.save(book5);
			bookServ.save(book6);
			bookServ.save(book7);
			bookServ.save(book8);
			bookServ.save(book9);
			bookServ.save(book10);

			System.out.println(book1);
			System.out.println("--------------------------");
			System.out.println(book2);
			System.out.println("--------------------------");
			System.out.println(book3);
			System.out.println("--------------------------");
			System.out.println(book4);
			System.out.println("--------------------------");

			List<Book> bookList1 = Arrays.asList(book1, book2, book5, book8, book10);
			List<Book> bookList2 = Arrays.asList(book3, book4, book6, book7, book9);
			List<Book> bookList3 = Arrays.asList(book1, book3, book5, book7, book9);

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
		} catch (Exception e) {

			System.out.println("Error in run method: " + e.getMessage());
		}
	}

}
