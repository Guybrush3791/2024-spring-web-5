package org.java.spring_crud6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCrud6Application {

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
	 */

	public static void main(String[] args) {
		SpringApplication.run(SpringCrud6Application.class, args);
	}

}
