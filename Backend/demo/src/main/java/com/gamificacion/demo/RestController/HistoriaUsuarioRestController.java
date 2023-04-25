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
import com.gamificacion.demo.DTO.PuntosHistoriaDTO;
import com.gamificacion.demo.Models.HistoriasUsuario;
import com.gamificacion.demo.Repository.IHistoriaUsuarioRepository;

@RestController
@RequestMapping("historia")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class HistoriaUsuarioRestController {
	
	@Autowired
	private IHistoriaUsuarioRepository historiaUsuarioRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
		

	
	
	public HistoriaUsuarioRestController(IHistoriaUsuarioRepository historiaUsuarioRepository,
			ObjectMapper objectMapper) {
		super();
		this.historiaUsuarioRepository = historiaUsuarioRepository;
		this.objectMapper = objectMapper;		
	}

	@PostMapping("/historia")
	public HistoriasUsuario save(@RequestBody LinkedHashMap linkedHashMap) {
		HistoriasUsuario h = objectMapper.convertValue(linkedHashMap, HistoriasUsuario.class);
		return historiaUsuarioRepository.save(h);
	}
	
	@PostMapping("/equipo/conteoPuntos")
	public double getByPuntosByEquipoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");		
		return historiaUsuarioRepository.conteoDePuntos(id);
	}
	
	@PostMapping("/equipo")
	public List<HistoriasUsuario> getByEquipoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return historiaUsuarioRepository.findByEquipo_Id(id);
	}
	
	@PostMapping("/tarea")
	public HistoriasUsuario getByTareaId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return historiaUsuarioRepository.findByTareas_Id(id);
	}
	
	@PostMapping("/nombre")
	public List<HistoriasUsuario> getByNombreLike(@RequestBody LinkedHashMap linkedHashMap){
		String n = (String) linkedHashMap.get("nombre");
		return historiaUsuarioRepository.findByNombreContains(n);
	}
}
