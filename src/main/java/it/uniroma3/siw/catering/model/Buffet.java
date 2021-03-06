package it.uniroma3.siw.catering.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Buffet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Chef chef;

	/* In questo contesto possiamo considerare un legame forte
	 * o meglio una composizione tra il buffet e i piatti, in particolare 
	 * a una cancellazione di un buffet ha senso che si propaghi a cascata sui piatti 
	 * di quel buffet la cancellazione.
	 */
	@OneToMany(mappedBy = "buffet",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
	private List<Piatto> piatti;

	@NotBlank
	private String nome;
	
	private String descrizione;

	public Buffet() {
		this.piatti = new ArrayList<>();
	}
	public Buffet(String nome) {
		this.piatti = new ArrayList<>();
		this.nome = nome;
	}
	
	public Buffet(String nome, Chef chef) {
		this.nome = nome;
		this.chef = chef;
		this.piatti = new ArrayList<>();
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}
	
	public void addPiatto(Piatto p) {
		this.getPiatti().add(p);
	}

}
