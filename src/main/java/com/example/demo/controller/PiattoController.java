package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Piatto;
import com.example.demo.service.PiattoService;

@Controller
public class PiattoController {
	@Autowired
	private PiattoService piattoService;
	
	@PostMapping("/piatto")
	public String insertBuffet(@Valid @ModelAttribute("/piatto") Piatto piatto, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("piatto", piatto);
			return "piatto.html";
		}
		return "piattoForm.html";
	}
	
	@GetMapping("/piatti")
	public String getBuffets(Model model) {
		List<Piatto> piatto = piattoService.findAll();
		model.addAttribute("piatto", piatto);
		return "piatti.html";
		
	}
	
	@GetMapping("/piatto/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model){
		Piatto piatto = piattoService.findById(id);
		model.addAttribute("piatto", piatto);
		return "piatto.html";	
	}
	
}
