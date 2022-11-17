package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamificacion.demo.Models.Dinamica;
import com.gamificacion.demo.Repository.IDinamicaRepository;

@RestController
@RequestMapping("dinamica")
public class DinamicaRestController {
	@Autowired
	private IDinamicaRepository dinamicaRepository;

	public DinamicaRestController(IDinamicaRepository dinamicaRepository) {
		super();
		this.dinamicaRepository = dinamicaRepository;
	}
	
	
	@PostMapping("/tarea/{id}")
	private Dinamica getDinamicaByTareaId(@PathVariable int id,@RequestBody LinkedHashMap linkedHashMap) {
		return dinamicaRepository.findByTareas_Id(id);
		
	}
}
