package com.gamificacion.demo.RestController;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Status;
import com.gamificacion.demo.Repository.IStatusRepository;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("status")
public class StatusRestController {
	@Autowired
	private IStatusRepository statusRepository;
	
	@Autowired
	private ObjectMapper objectMapper;	
	
	public StatusRestController(IStatusRepository statusRepository, ObjectMapper objectMapper) {
		super();
		this.statusRepository = statusRepository;
		this.objectMapper = objectMapper;
	}

	@GetMapping("/status")
	private List<Status> getAllStatus() {
		return statusRepository.findAll();
	}
	
	@PostMapping("/status")
	private Status save(@RequestBody LinkedHashMap linkedHashMap){
		Status s = objectMapper.convertValue(linkedHashMap, Status.class);		
		return statusRepository.save(s);					
	}
	
	@PostMapping("/equipo")
	private List<Status> getByEquipoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return statusRepository.findByEquipo_Id(id);
	}
	
	@PostMapping("/clase")
	private List<Status> getStatusByClassToWork(@RequestBody LinkedHashMap linkedHashMap){
		String classToWork = (String)linkedHashMap.get("clase");				
		return statusRepository.findByClases_nombre(classToWork);
	}
	
	//@GetMapping("/get")
	
}
