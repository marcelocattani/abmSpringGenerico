package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.dtos.PersonaDto;
import main.services.PersonaService;

@Controller
@RestController
@RequestMapping(path = "api/v1/persona")
public class PersonaController extends BaseController<PersonaDto> {

	private PersonaService personaService;

	public PersonaController(PersonaService personaService) {
		super(personaService);
		this.personaService = personaService;
	}

}
