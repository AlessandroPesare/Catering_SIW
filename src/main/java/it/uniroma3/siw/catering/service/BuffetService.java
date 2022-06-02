package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {	
	@Autowired
	private BuffetRepository buffetRepo;
	
	//ci pensa springboot
	@Transactional
	public void save(Buffet buffet) {
		buffetRepo.save(buffet);
	}
	
	//interrogazione non è transazionale
	public Buffet findById(Long id) {
		return buffetRepo.findById(id).get();
	}
	
	public List<Buffet> findAll(){
		List<Buffet> buffet = new ArrayList<Buffet>();
		for(Buffet b: buffetRepo.findAll()) {
			buffet.add(b);
		}
		return buffet;
	}
	
	public boolean alreadyExists(Buffet buffet) {
		return buffetRepo.existsByNomeAndDescrizione(buffet.getNome(), buffet.getDescrizione());
	}
	@Transactional
	public void deleteById(Long id) {
		buffetRepo.deleteById(id);
	}

	public void deleteBuffet(Long id) {
		buffetRepo.deleteById(id);
	}

	public List<Buffet> findAllByChef(Chef chef) {
		List<Buffet> buffets = buffetRepo.findAllByChef(chef);
		return buffets;
	}
}
