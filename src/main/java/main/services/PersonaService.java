package main.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import main.dtos.PersonaDto;
import main.entities.PersonaEntity;
import main.repositories.PersonaRepository;

@Service
public class PersonaService extends BaseService<PersonaEntity, PersonaDto> {

	private PersonaRepository personaRepository;

	public PersonaService(PersonaRepository personaRepository) {
		super(PersonaDto.class, PersonaEntity.class, personaRepository);
		this.personaRepository = personaRepository;
	}

}
