package main.controllers;

import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import main.services.IBaseService;

public class BaseController<DTO> {
	private IBaseService service;

	public BaseController(IBaseService service) {
		this.service = service;
	}

	@GetMapping("/")
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.FOUND).body(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Error.\"}");
		}
	}

	@GetMapping("/{id}")
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.FOUND).body(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error.\"}");
		}
	}

	@PostMapping("/")
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity post(@RequestBody DTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.FOUND).body(service.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error.\"}");
		}
	}

	@PutMapping("/{id}")
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity put(@PathVariable int id, @RequestBody DTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.FOUND).body(service.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error.\"}");
		}
	}

	@DeleteMapping("/{id}")
	@CrossOrigin(origins = "*")
	@Transactional
	public ResponseEntity delete(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.delete(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error.\"}");
		}
	}

}