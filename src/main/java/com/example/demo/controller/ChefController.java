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

import com.example.demo.model.Chef;
import com.example.demo.service.ChefService;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;
	
	@PostMapping("/chef")
	public String insertBuffet(@Valid @ModelAttribute("/chef") Chef chef, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "chef.html";
		}
		return "chefForm.html";
	}
	
	@GetMapping("/chefs")
	public String getBuffets(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chef",chefs);
		return "chefs.html";
		
	}
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model){
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "chef.html";	
	}
	
}
