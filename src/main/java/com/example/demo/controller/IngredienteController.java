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

import com.example.demo.model.Ingrediente;
import com.example.demo.service.IngredienteService;

@Controller
public class IngredienteController {
	@Autowired
	private IngredienteService ingredienteService;

	@PostMapping("/ingrediente")
	public String insertBuffet(@Valid @ModelAttribute("/ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "ingrediente.html";
		}
		return "ingredienteForm.html";
	}

	@GetMapping("/ingredienti")
	public String getBuffets(Model model) {
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingrediente",ingredienti);
		return "ingredienti.html";

	}

	@GetMapping("/ingrediente/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model){
		Ingrediente ingrediente = ingredienteService.findById(id);
		model.addAttribute("ingrediente", ingrediente);
		return "ingrediente.html";	
	}

}
