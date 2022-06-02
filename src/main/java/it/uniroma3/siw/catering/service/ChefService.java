package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository chefRepo;
	
	//ci pensa springboot
	@Transactional
	public void save(Chef chef) {
		chefRepo.save(chef);
	}
	
	//interrogazione non è transazionale
	public Chef findById(Long id) {
		return chefRepo.findById(id).get();
	}
	
	public List<Chef> findAll(){
		List<Chef> chefs = new ArrayList<Chef>();
		for(Chef c: chefRepo.findAll()) {
			chefs.add(c);
		}
		return chefs;
	}
	
	public boolean alreadyExists(Chef chef) {
		return chefRepo.existsByNomeAndCognomeAndNazione(chef.getNome(), chef.getCognome(),chef.getNazione());
	}
	@Transactional
	public void deleteById(Long id) {
		chefRepo.deleteById(id);
	}

	public void updateChef(Chef oldChef) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteChefById(Long id) {
		// TODO Auto-generated method stub
		
	}
}
