package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Ingrediente;
import it.uniroma3.siw.catering.model.Piatto;
import it.uniroma3.siw.catering.service.IngredienteService;
import it.uniroma3.siw.catering.service.PiattoService;
import it.uniroma3.siw.catering.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	
	@Autowired private IngredienteService ingredienteService;
	
	@Autowired private PiattoService piattoService;
	
	@Autowired private IngredienteValidator ingredienteValidator;
	
	/*
	 * ADMIN
	 */
	@GetMapping("/administration/ingredienti")
	public String listBuffets(Model model) {
		model.addAttribute("ingredienti", ingredienteService.findAll());

		return "admin/ingrediente/ingredienti.html";
	}
	
	@GetMapping("/administration/ingredienti/piatto/{piatto_id}")
	public String listIngredientiPiatto(@PathVariable Long piatto_id, Model model) {
		Piatto piatto = piattoService.findById(piatto_id);
		model.addAttribute("piatto", piatto);
		model.addAttribute("ingredienti", piatto.getIngredienti());
		
		return "admin/ingrediente/ingredienti_for_piatto.html";
	}
	
	@GetMapping("/administration/ingredienti/new/{piatto_id}")
	public String createIngredienteForm(@PathVariable("piatto_id") Long id, Model model) {
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		model.addAttribute("idPiatto", id);
		return "admin/ingrediente/create_ingrediente.html";
	}

	@PostMapping("/administration/ingredienti/{idPiatto}")
	public String addIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente,@PathVariable("idPiatto") Long id, BindingResult bindingResults, Model model) {
		this.ingredienteValidator.validate(ingrediente, bindingResults);
		if(!bindingResults.hasErrors()) {
			Piatto p = piattoService.findById(id);
			p.getIngredienti().add(ingrediente);
			piattoService.save(p);
			model.addAttribute("ingrediente", model);
			return "redirect:/administration/ingredienti";
		}
		else
			return "admin/ingrediente/create_ingrediente.html";
	}
}