package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Periferica;
import com.example.demo.service.PerifericaService;

@Component
public class PerifericaValidator implements Validator {

	@Autowired
	private PerifericaService perifericaService;

	final Integer MAX_NAME_LENGTH = 30;
	final Integer MIN_NAME_LENGTH = 2;
	final Integer MAX_DESC_LENGTH = 500;
	final Integer MIN_DESC_LENGTH = 2;

	
	@Override
	public void validate(Object o, Errors errors) {
		Periferica p = (Periferica) o;
		String nome = p.getNome().trim();
		String desc = p.getDescrizione().trim();
		String tip = p.getTipologia();

		if (nome.isEmpty())
			errors.rejectValue("nome", "required");
		
		else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
			errors.rejectValue("nome", "size");
		
		else if (desc.isEmpty())
			errors.rejectValue("descrizione", "required");
		
		else if (desc.length() < MIN_DESC_LENGTH || nome.length() > MAX_DESC_LENGTH)
			errors.rejectValue("descrizione", "size");
	
		//TODO controlla se funziona bene
		else if(this.perifericaService.alreadyExists(p))
			errors.rejectValue("nome" ,"duplicate");
		
		//nuovo
		if( !tip.equals("display") && !tip.equals("tastiera") && !tip.equals("mouse")
				&&	!tip.equals("cuffia") )
			errors.rejectValue("tipologia", "inesistente");
	}
		

	@Override
	public boolean supports(Class<?> clazz) {
		return Periferica.class.equals(clazz);
	}

}
