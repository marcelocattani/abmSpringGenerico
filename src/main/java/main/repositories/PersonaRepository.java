package main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entities.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Integer> {

}
