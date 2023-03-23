package com.gamificacion.demo.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Equipo;
import com.gamificacion.demo.Models.Proyecto;
import com.gamificacion.demo.Repository.IProyectoRepository;
import com.gamificacion.demo.Services.FilesStorageService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("proyecto")
public class ProyectoRestController {
	@Autowired
	private IProyectoRepository proyectoRepository;
	
	@Autowired	
	ObjectMapper objectMapper;	
	
	
	
	public ProyectoRestController(IProyectoRepository proyectoRepository, ObjectMapper objectMapper,FilesStorageService storageService) {
		super();
		this.proyectoRepository = proyectoRepository;
		this.objectMapper = objectMapper;
	}

	@PostMapping("/id")
	public Proyecto getById(@RequestBody LinkedHashMap linkedHashMap) {
		int id = (int) linkedHashMap.get("id");
		return proyectoRepository.findById(id);
	}

	@PostMapping("/proyecto")
	public Proyecto saveProyecto(@RequestBody LinkedHashMap linkedHashMap) {
		Proyecto p = objectMapper.convertValue(linkedHashMap, Proyecto.class);
		return proyectoRepository.save(p);
	}
	
	@PostMapping("/equipo")
	public List<Proyecto> getByProyecto(@RequestBody LinkedHashMap linkedHashMap) {		
		Equipo e = objectMapper.convertValue(linkedHashMap, Equipo.class);
		return proyectoRepository.findByEquipo_Id(e.getId());
	}
	
	@PostMapping("/all")
	public List<Proyecto> getAll(@RequestBody LinkedHashMap linkedHashMap){
		return proyectoRepository.findAll();
	}
		
	@PostMapping("/nombre")
	public List<Proyecto> getByNombreLike(@RequestBody LinkedHashMap linkedHashMap){
		String nombre = (String)linkedHashMap.get("nombre");
		return proyectoRepository.findByNombreContains(nombre);
	}
	
	@PostMapping("/user")
	public List<Proyecto> getByUsuarioId(@RequestBody LinkedHashMap linkedHashMap){
		int id =(int)linkedHashMap.get("id");
		return proyectoRepository.findByEquipo_Usuarios_Id(id);
	}
	
	@PostMapping("/proyectoAdmin")
	public Page<Proyecto> getEquipoAdmin(@RequestBody LinkedHashMap linkedHashMap){
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
				return proyectoRepository.findAllByOrderByIdAsc(pageable);		
			}
		}		
		return proyectoRepository.findAllByOrderByIdDesc(pageable);		
	}
	
}
