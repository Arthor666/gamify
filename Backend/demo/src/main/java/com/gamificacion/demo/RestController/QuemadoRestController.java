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
import com.gamificacion.demo.Models.Quemado;
import com.gamificacion.demo.Repository.IQuemadoRepository;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("quemado")
public class QuemadoRestController {
	
	@Autowired	
	private ObjectMapper objectMapper;	
	
	@Autowired
	private IQuemadoRepository quemadoRepository;

	public QuemadoRestController(ObjectMapper objectMapper, IQuemadoRepository quemadoRepository) {
		super();
		this.objectMapper = objectMapper;
		this.quemadoRepository = quemadoRepository;
	}
	
	@PostMapping("/equipo")
	private List<Quemado> getByEquipoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return quemadoRepository.findByEquipo_Id(id);
	}

}
