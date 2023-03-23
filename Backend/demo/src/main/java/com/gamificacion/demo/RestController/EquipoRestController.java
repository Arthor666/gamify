package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.DTO.CountDTO;
import com.gamificacion.demo.Models.Equipo;
import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Repository.IEquipoRepository;

@RestController
@RequestMapping("equipo")
@CrossOrigin(origins = "*",allowedHeaders = "*")
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
		
	
	
	@PostMapping("/equipoAdmin")
	public Page<Equipo> getEquipoAdmin(@RequestBody LinkedHashMap linkedHashMap){
		int page = 0;
		int size = 15;
		if(linkedHashMap.containsKey("page") && linkedHashMap.containsKey("size")) {
			page = (int) linkedHashMap.get("page");
			size = (int) linkedHashMap.get("size");					
		}
		Pageable pageable = PageRequest.of(page, size);
		if(linkedHashMap.containsKey("filter")){
			String filter =(String)linkedHashMap.get("filter"); 
			if( filter.equals("asc") ) {				
				return equipoRepository.findAllByOrderByIdAsc(pageable);		
			}
		}		
		return equipoRepository.findAllByOrderByIdDesc(pageable);		
	}
	
	@PostMapping("/user")
	public List<Equipo> getEquipoUser(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return equipoRepository.findByUsuarios_Id(id);
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
		equipo = equipoRepository.save(equipo);
		for(Usuario u:equipo.getUsuarios()) {
			equipoRepository.updateAvalableUser(u.getId());
		}
		return equipo;
	}
	
	@PostMapping("/all")
	public List<Equipo> getAll(@RequestBody LinkedHashMap linkedHashMap){
		return equipoRepository.findAll();
	}
	
	@PostMapping("/numProyectos")
	public CountDTO getCountProyectos(@RequestBody LinkedHashMap linkedHashMap) {
		CountDTO c = new CountDTO();
		int id = (int) linkedHashMap.get("id");
		c.setCount(equipoRepository.countProyectosNoFinalizados(id));
		return c;
	}
	
	@PostMapping("/poryecto")
	public Equipo getByProyectoId(@RequestBody LinkedHashMap linkedHasMap){
		int id = (int) linkedHasMap.get("id");
		return equipoRepository.findByProyectos_Id(id);
	}
	
	@PostMapping("/nombre")
	public List<Equipo> getByNombreLike(@RequestBody LinkedHashMap linkedHashMap){
		String n  = (String) linkedHashMap.get("nombre");
		return equipoRepository.findByNombreContains(n);
	}
}
