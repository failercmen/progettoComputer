package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.validator.ComponenteValidator;
import com.example.demo.model.Componente;
import com.example.demo.service.ComponenteService;

@Controller
public class ComponenteController {
	
	@Autowired
	private ComponenteService componenteService;
	
    @Autowired
    private ComponenteValidator componenteValidator;
    
    
    @RequestMapping(value = "/componenti", method = RequestMethod.GET)
    public String getListaComponenti(Model model) {
    		model.addAttribute("listaComponenti", this.componenteService.tutti());
    		return "listaComponenti.html";
    }
    
    @RequestMapping(value = "/admin/componente", method = RequestMethod.GET)
    public String addChef(Model model) {
    	model.addAttribute("componente", new Componente());
        return "componenteForm.html";
    }
    
    @RequestMapping(value = "/admin/componente", method = RequestMethod.POST)
    public String addChef(@ModelAttribute("componente") Componente componente, Model model, BindingResult bindingResult) {
    	this.componenteValidator.validate(componente, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.componenteService.inserisci(componente);
            model.addAttribute("listaComponenti", this.componenteService.tutti());
            return "listaComponenti.html";
        }
        return "componenteForm.html";
    }
    
    @RequestMapping(value = "/componente/{id}", method = RequestMethod.GET)
    public String getChef(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("componente", this.componenteService.componentePerId(id));
    	return "componente.html";
    }
    
      

}
