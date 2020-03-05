package main.services;

import java.util.List;

import javax.transaction.Transactional;

public interface IBaseService<DTO> {

	@Transactional
	public List<DTO> findAll() throws Exception;

	@Transactional
	public DTO findById(int id) throws Exception;

	@Transactional
	public DTO save(DTO dto) throws Exception;

	@Transactional
	public DTO update(int id, DTO dto) throws Exception;

	@Transactional
	public boolean delete(int id) throws Exception;

}
