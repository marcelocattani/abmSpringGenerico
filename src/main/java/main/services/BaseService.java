package main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import main.dtos.BaseDto;
import main.entities.BaseEntity;
import main.repositories.PersonaRepository;

//Paginacion
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BaseService<ENTITY extends BaseEntity, DTO extends BaseDto> implements IBaseService<DTO> {

	// Apuntador al repositorio
	private JpaRepository repository;

	//
	private ModelMapper modelMapper = new ModelMapper();

	// Elementos para modelMapper
	private Class dtoClass;
	private Class entityClass;

	public BaseService(Class dtoClass, Class entityClass, JpaRepository repository) {
		super();
		this.dtoClass = dtoClass;
		this.entityClass = entityClass;
		this.repository = repository;
	}

	@Override
	public List<DTO> findAll(int page, int size) throws Exception {

		List<DTO> dtos = new ArrayList<DTO>();

		try {
			Pageable pageable = PageRequest.of(page, size);
			Page<ENTITY> entities = repository.findAll(pageable);

			for (ENTITY item : entities.getContent()) {

				DTO dto = (DTO) this.modelMapper.map(item, dtoClass);
				dtos.add(dto);
				
			}

		} catch (Exception e) {
			throw new Exception(e);
		}
		
		return dtos;
	}

	@Override
	public DTO findById(int id) throws Exception {
		Optional<ENTITY> entityOptional = this.repository.findById(id);
		try {
			ENTITY entity = entityOptional.get();
			return (DTO) this.modelMapper.map(entity, dtoClass);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public DTO save(DTO dto) throws Exception {
		ENTITY entity;
		try {
			entity = (ENTITY) this.modelMapper.map(dto, entityClass);
			entity = (ENTITY) this.repository.save(entity);
			return (DTO) this.modelMapper.map(entity, dtoClass);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public DTO update(int id, DTO dto) throws Exception {
		Optional<ENTITY> entityOptional = this.repository.findById(id);
		try {
			ENTITY entity = entityOptional.get();
			ENTITY entityParams = (ENTITY) this.modelMapper.map(dto, entityClass);
			entityParams.setId(id);
			entity = (ENTITY) this.repository.save(entityParams);
			return (DTO) this.modelMapper.map(entity, dtoClass);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public boolean delete(int id) throws Exception {
		try {
			this.repository.deleteById(id);
			return true;
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
