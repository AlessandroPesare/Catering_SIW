package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Buffet;
import com.example.demo.repository.BuffetRepository;

@Service
public class BuffetService {
	@Autowired
	private BuffetRepository buffetRepo;

	@Transactional
	public void save(Buffet buffet) {
		buffetRepo.save(buffet);
	}

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
}
