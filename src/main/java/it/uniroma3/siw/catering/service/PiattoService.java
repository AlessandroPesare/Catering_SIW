package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.repository.PiattoRepository;

@Service
public class PiattoService {
	@Autowired
	private PiattoRepository piattoRepo;
	
	@Transactional
	public void save(Piatto piatto) {
		piattoRepo.save(piatto);
	}
	
	public Piatto findById(Long id) {
		return piattoRepo.findById(id).get();
	}
	
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p: piattoRepo.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}
	
}
