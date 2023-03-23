package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Clases;
import com.gamificacion.demo.Models.Status;
import com.gamificacion.demo.Repository.IClasesRepository;
import com.gamificacion.demo.Repository.IStatusRepository;

@RestController
@RequestMapping("clases")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Transactional
public class ClasesRestController {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	IClasesRepository clasesRepository;
	
	@Autowired
	IStatusRepository statusRepository;

	
	
	public ClasesRestController(ObjectMapper objectMapper, IClasesRepository clasesRepository,
			IStatusRepository statusRepository) {
		super();
		this.objectMapper = objectMapper;
		this.clasesRepository = clasesRepository;
		this.statusRepository = statusRepository;
	}



	@PostMapping("/clases")
	public Clases save(@RequestBody LinkedHashMap linkedHashMap) {		
		Clases c = objectMapper.convertValue(linkedHashMap, Clases.class);
		Clases cAux = clasesRepository.findByNombre(c.getNombre());
		if(cAux == null) {						
			cAux = clasesRepository.save(c);
		}
		cAux.getStatuses().addAll(c.getStatuses());
		Status s = null;
		if(cAux.getStatuses().get((cAux.getStatuses().size()-1)).getClases() == null ) {				
			s = statusRepository.findById(cAux.getStatuses().get((cAux.getStatuses().size()-1)).getId()).get();
		}
		s.getClases().add(cAux);
		statusRepository.save(s);
		return cAux;
	}
	
}
