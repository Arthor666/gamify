package com.gamificacion.demo.RestController;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Grupo;
import com.gamificacion.demo.Repository.IGrupoRepository;
import com.gamificacion.demo.Repository.IUserRepository;

@RestController
@RequestMapping("grupo")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class GrupoRestController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IGrupoRepository grupoRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	
	public GrupoRestController(ObjectMapper objectMapper, IGrupoRepository grupoRepository, IUserRepository userRepository) {
		super();
		this.objectMapper = objectMapper;
		this.grupoRepository = grupoRepository;		
		this.userRepository = userRepository;
	}



	@PostMapping("/profesor")
	public List<Grupo> getByProfesorId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return grupoRepository.findByProfesor_Id(id);
	}
	
	@PostMapping("/alumno")
	public List<Grupo> getByAlumnoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return grupoRepository.findByAlumnos_Id(id);
	}
	
	@PostMapping("/alumno/codigo")
	public Grupo inscribirAlumno(@RequestBody LinkedHashMap linkedHashMap){
		String codigo = (String) linkedHashMap.get("codigo");
		int id = (int) linkedHashMap.get("id");
		Grupo g = grupoRepository.findByCodigoAcceso(codigo);
		g.getAlumnos().add(userRepository.findById(id).get());
		grupoRepository.save(g);
		return g;
	}
	
	@PostMapping("/grupo")
	public Grupo save(@RequestBody LinkedHashMap linkedHashMap) {
		Grupo grupo = objectMapper.convertValue(linkedHashMap,Grupo.class);
		grupo.generateUniqueCode();
		grupo.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		return grupoRepository.save(grupo);
	}
}
