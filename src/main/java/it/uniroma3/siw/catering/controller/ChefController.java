package it.uniroma3.siw.catering.controller;

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

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.ChefValidator;

@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;
	@Autowired
	private ChefValidator validator;

	@PostMapping("/administration/chefs")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResult, Model model) {
		validator.validate(chef, bindingResult);
		if(!bindingResult.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "administration/chef.html";
		}
		return "admin/chef/new_chef.html";
	}
	
	@GetMapping("/administration/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);

		return "admin/chef/chefs.html";
	}

	@GetMapping("/administration/chef/{id}")
	public String getChef(@PathVariable("id") Long id, Model model){
		Chef chef = chefService.findById(id);
		model.addAttribute("chef", chef);
		return "admin/chef/edit_chef.html";	
	}

	@PostMapping("/administration/chefs/{id}")
	public String updateChef(@PathVariable Long id, @Valid @ModelAttribute("chef") Chef chef, 
			BindingResult bindingResults, Model model) {
		if(!bindingResults.hasErrors()) {
			Chef oldChef = chefService.findById(id);
			oldChef.setId(chef.getId());
			oldChef.setNome(chef.getNome());
			oldChef.setCognome(chef.getCognome());
			oldChef.setNazione(chef.getNazione());

			chefService.updateChef(oldChef);
			model.addAttribute("chef", model);
			return "redirect:/administration/chefs";
		}
		else
			return "admin/chef/edit_chef.html";
	}
	
	@GetMapping("/administration/chefs/new")
	public String getChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "admin/chef/new_chef.html";
	}
	
	@GetMapping("/administration/chefs/del/{id}")
	public String deleteChef(@PathVariable Long id) {
		chefService.deleteChefById(id);
		return "/administration/chefs";
	}

}
