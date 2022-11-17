package com.gamificacion.demo.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Subequipo;
import com.gamificacion.demo.Repository.ISubequipoRepository;
import com.gamificacion.demo.Repository.ITareaRepository;
import com.google.gson.JsonArray;

@RestController
@RequestMapping("subequipo")
public class SubequipoRestController {

	@Autowired
	private ISubequipoRepository subequipoRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private SubequipoRestController(ISubequipoRepository subequipoRepository) {
		this.subequipoRepository = subequipoRepository;
	}
	
	@PostMapping("subequipo")
	private Subequipo saveSubequipo(@RequestBody LinkedHashMap linkedHashMap) {
		linkedHashMap.remove("signature");
		Subequipo subequipo = objectMapper.convertValue(linkedHashMap,Subequipo.class);
		return subequipoRepository.save(subequipo);
	}
	

	/*@PostMapping("subequipos")
	private List<Subequipo> saveSubequipos(@RequestBody LinkedHashMap linkedHashMap) throws IllegalArgumentException, IOException {
		linkedHashMap.remove("signature");				
		List<Subequipo subequipos = objectMapper.readValue(objectMapper.convertValue(linkedHashMap, JsonArray.class), objectMapper.getTypeFactory().constructCollectionType(List.class, Subequipo.class));
		return null;
	}*/
	
	@PostMapping("/tarea/{id}")
	private Subequipo getSubequipo(@PathVariable int id,@RequestBody LinkedHashMap linkedHashMap) {
		return subequipoRepository.findByTarea_Id(id);
	}
	
}
