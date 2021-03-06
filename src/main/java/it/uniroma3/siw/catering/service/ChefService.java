package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired private ChefRepository chefRepository;

	@Transactional
	public Chef save(Chef chef) {
		return chefRepository.save(chef);
	}

	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}

	public List<Chef> findAll(){
		List<Chef> chefs = new ArrayList<Chef>();
		for(Chef c : chefRepository.findAll()) {
			chefs.add(c);
		}
		return chefs;
	}

	@Transactional
	public Chef updateChef(Chef chef) {
		return this.save(chef);
	}

	@Transactional
	public void deleteChefById(Long id) {
		chefRepository.deleteById(id);
	}

	public boolean alreadyExists(Chef chef) {
		return chefRepository.existsByNomeAndCognome(chef.getNome(), chef.getCognome());
	}
	
	public List<Buffet> getBuffetsOfChef(Long id){
		Chef c = this.chefRepository.findById(id).get();
		return c.getBuffets();
	}
}