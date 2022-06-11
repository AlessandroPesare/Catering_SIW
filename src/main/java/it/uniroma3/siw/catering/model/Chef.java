package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Chef {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@NotBlank
	private String nazione;
	
	@OneToMany(mappedBy = "chef", cascade = {CascadeType.REMOVE})
	private List<Buffet> buffets;

	public Chef() {
		this.buffets = new ArrayList<Buffet>();
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public List<Buffet> getBuffets() {
		return buffets;
	}

	public void setBuffet(List<Buffet> buffet) {
		this.buffets = buffet;
	}

	public void addBuffet(Buffet buffet) {
		this.getBuffets().add(buffet);		
	}
}