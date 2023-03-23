package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Rol;
import com.gamificacion.demo.Repository.IRolRepository;

@RestController
@RequestMapping("rol")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class RolRestController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IRolRepository rolRepository;

	public RolRestController(ObjectMapper objectMapper, IRolRepository rolRepository) {
		super();
		this.objectMapper = objectMapper;
		this.rolRepository = rolRepository;
	}
	
	@PostMapping("/all")
	public List<Rol> getAll(@RequestBody LinkedHashMap linkedHashMap){
		return rolRepository.findAll();
	}

}
