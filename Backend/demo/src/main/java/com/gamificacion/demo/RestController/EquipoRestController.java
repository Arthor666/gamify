package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Equipo;
import com.gamificacion.demo.Repository.IEquipoRepository;

@RestController
@RequestMapping("equipo")
public class EquipoRestController {
	
	@Autowired
	IEquipoRepository equipoRepository;
	
	@Autowired
	ObjectMapper objectMapper;

	public EquipoRestController(IEquipoRepository equipoRepository, ObjectMapper objectMapper) {
		super();
		this.equipoRepository = equipoRepository;
		this.objectMapper = objectMapper;
	}
	
	@PostMapping("/equipo")
	public Equipo saveEquipo(@RequestBody LinkedHashMap linkedHashMap) {
		/*
		 * {
			    "isActive":"true",
			    "nombre":"Equipo Dinamita",
			    "jefe":{
			        "id":1
			    },
			    "usuarios":[        
			        {
			            "id":4
			        }
			    ]
			}
		 * 
		 * */
		Equipo equipo = objectMapper.convertValue(linkedHashMap,Equipo.class);
		return equipoRepository.save(equipo);
	}

}
