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

	@Autowired private ChefService chefService;
	
	@Autowired private ChefValidator chefValidator;
/*
 * USER
 */
	@GetMapping("info/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = this.chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "info/chefs.html";
	}
	
	@GetMapping("info/chef/{id}")
	public String getSingoloChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("chef", this.chefService.findById(id));
		model.addAttribute("buffets", this.chefService.getBuffetsOfChef(id));
		return "info/chef.html";
	}
	
/*
 * ADMIN
 */
	@GetMapping("/administration/chefs")
	public String listChefs(Model model) {
		model.addAttribute("chefs", chefService.findAll());
		return "admin/chef/chefs.html";
	}

	@GetMapping("/administration/chefs/new")
	public String createChefForm(Model model) {
		model.addAttribute("chef", new Chef());
		return "admin/chef/create_chef.html";
	}

	@PostMapping("/administration/chefs")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		chefValidator.validate(chef, bindingResults);
		if(!bindingResults.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", model);
			return this.listChefs(model);
		}
		else
			return "admin/chef/create_chef.html";
	}

	@GetMapping("/administration/chefs/edit/{id}")
	public String updateChefForm(@PathVariable Long id, Model model) {
		model.addAttribute("chef", chefService.findById(id));
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
	
	@GetMapping("/administration/chefs/del/{id}")
	public String deleteChef(@PathVariable Long id) {
		chefService.deleteChefById(id);
		return "redirect:/administration/chefs";
	}
	
	@GetMapping("/our_chefs")
	public String showOurChefs(Model model) {
		model.addAttribute("chefs", chefService.findAll());

		return "our_chefs.html";
	}
}