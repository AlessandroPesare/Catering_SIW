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

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.BuffetValidator;

@Controller
public class BuffetController {

	@Autowired private BuffetService buffetService;
	
	@Autowired private ChefService chefService;

	@Autowired private BuffetValidator buffetValidator;

/*
 * USER
 */
	@GetMapping("info/buffets")
	public String getBuffets(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		model.addAttribute("buffets", buffets);
		return "info/buffets.html";
	}
	
	@GetMapping("info/buffet/{id}")
	public String getSingoloBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return "info/buffet.html";
	}
/*
 * ADMIN
 */
	@GetMapping("/administration/buffets")
	public String listBuffets(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/buffet/buffets.html";
	}
	
	//@ModelAttribute Piatto piattoRicevutodaForm;
	
	//prelevare dal db
	//piattoPrelevatoDb.setNome(piattoRicevutodaForm.getNome())
	//lo stesso va fatto per descrizione
	//save()
	
	
	
	@GetMapping("/administration/buffets/chef/{chef_id}")
	public String listBuffetsChef(@PathVariable Long chef_id, Model model) {
		Chef chef = chefService.findById(chef_id);
		model.addAttribute("buffets", buffetService.findAllByChef(chef));
		model.addAttribute("chef", chef);
		
		return "admin/buffet/buffets_for_chef.html";
	}
	
	@GetMapping("/administration/buffets/new/{chef_id}")
	public String createBuffetForm(@PathVariable Long chef_id, Model model) {
		Buffet buffet = new Buffet();
		model.addAttribute("buffet", buffet);
		model.addAttribute("chef_id", chef_id);
		
		return "admin/buffet/create_buffet.html";
	}
	
	@PostMapping("/administration/buffets/{chef_id}")
	public String addBuffet(@PathVariable("chef_id") Long chefId, @Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		//recupero lo chef a cui è associato l'id
		Chef chef = chefService.findById(chefId);
		chef.addBuffet(buffet);
		buffet.setChef(chef);
		//validazione dati del buffet
		this.buffetValidator.validate(buffet, bindingResults);
		
		if(!bindingResults.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/administration/chefs";
		}
		else
			return "admin/buffet/create_buffet.html";
	}
	
	@GetMapping("/administration/buffets/del/{id}")
	public String deleteBuffet(@PathVariable Long id) {
		buffetService.deleteBuffet(id);
		return "redirect:/administration/buffets";
	}
}