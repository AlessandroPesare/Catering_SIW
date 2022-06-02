package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository ingredienteRepo;
	
	@Transactional
	public void save(Ingrediente ingrediente) {
		ingredienteRepo.save(ingrediente);
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepo.findById(id).get();
	}
	
	public List<Ingrediente> findAll(){
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
		for(Ingrediente i: ingredienteRepo.findAll()) {
			ingredienti.add(i);
		}
		return ingredienti;	
	}

	public boolean alreadyExists(Ingrediente ingrediente) {
		return ingredienteRepo.existsByNomeAndOrigineAndDescrizione(ingrediente.getNome(), ingrediente.getOrigine(),ingrediente.getDescrizione());
	}
	
	@Transactional
	public void remove(Long id) {
		ingredienteRepo.deleteById(id);
	}
}
