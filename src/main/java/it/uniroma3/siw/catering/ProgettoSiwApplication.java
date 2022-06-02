package it.uniroma3.siw.catering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * Dobbiamo progettare e implementare il sistema informativo di una 
 * società che offre servizi di catering.La società offre diversi buffet:
 * ogni buffet è proposto da uno chef e contiene uno o più piatti.
 * Ogni buffet ha un nome e una descrizione. Uno chef può proporre uno o
 * più buffet. Per ogni chef sono di interesse nome, cognome, nazionalità.
 * Per ogni piatto proposto in un buffet sono di interesse il nome, una
 * descrizione, l'elenco degli ingredienti. Per ogni ingrediente è di
 * interesse il nome, l'origine, una descrizione. 
 * Le specifiche possono essere completate dal candidato,
 *  laddove ritenuto necessario. 
 */
@SpringBootApplication
public class ProgettoSiwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoSiwApplication.class, args);
	}

}
