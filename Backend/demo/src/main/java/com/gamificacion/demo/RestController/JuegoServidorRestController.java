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
import com.gamificacion.demo.Models.JuegoServidor;
import com.gamificacion.demo.Repository.IJuegoServidorRepository;

@RestController
@RequestMapping("juego")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class JuegoServidorRestController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IJuegoServidorRepository juegoServidorRepository;

	public JuegoServidorRestController(ObjectMapper objectMapper, IJuegoServidorRepository juegoServidorRepository) {
		super();
		this.objectMapper = objectMapper;
		this.juegoServidorRepository = juegoServidorRepository;
	}
	
	@PostMapping("/equipo")
	public List<JuegoServidor>  getByEquipoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return juegoServidorRepository.findByEquipo_Id(id);
	}
	
	@PostMapping("/juego")
	public JuegoServidor save(@RequestBody JuegoServidor juegoServidor) {
		
		return juegoServidorRepository.save(juegoServidor);
	}
}
