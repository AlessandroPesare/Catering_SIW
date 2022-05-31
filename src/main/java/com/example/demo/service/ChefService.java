package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Chef;
import com.example.demo.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository chefRepo;
	
	@Transactional
	public void save(Chef chef) {
		chefRepo.save(chef);
	}
	
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
}
