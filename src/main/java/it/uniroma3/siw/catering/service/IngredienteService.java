package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired private IngredienteRepository ingredienteRepository;
	@Autowired private PiattoService piattoService;
		
	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	
	public Ingrediente findById(Long id) {
		return this.ingredienteRepository.findById(id).get();
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti = new ArrayList<>();
		for(Ingrediente i: ingredienteRepository.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;
	}
	
	public boolean alreadyExists(Ingrediente ingrediente) {
		return ingredienteRepository.existsByNomeAndOrigine(ingrediente.getNome(), ingrediente.getOrigine());

	}

	public void deleteIngrediente(Long id) {
		List<Piatto> piatti = piattoService.findAll();
		for(Piatto piatto: piatti){
			for(Ingrediente i: piatto.getIngredienti()) {
				if(i.getId()==id) {
					piatto.getIngredienti().remove(i);
					piattoService.save(piatto);
					break;
				}
			}
		}
		this.ingredienteRepository.deleteById(id);
	}

	public Ingrediente findByNome(String nome) {
		return this.ingredienteRepository.findByNome(nome);
	}

}