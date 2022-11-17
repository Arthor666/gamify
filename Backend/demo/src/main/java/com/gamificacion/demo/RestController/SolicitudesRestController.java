package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Solicitud;
import com.gamificacion.demo.Repository.ISolicitudRepository;

@RestController
@RequestMapping("solicitud")
public class SolicitudesRestController {
	
	@Autowired
	private ISolicitudRepository solicitudRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private SolicitudesRestController(ISolicitudRepository solicitudRepository,ObjectMapper objectMapper) {
		this.solicitudRepository = solicitudRepository; 
		this.objectMapper = objectMapper;
	}
	
	@PostMapping("solicitud")
	private Solicitud saveSolicitud(@RequestBody LinkedHashMap linkedHashMap) {
		linkedHashMap.remove("signature");
		Solicitud solicitud = objectMapper.convertValue(linkedHashMap, Solicitud.class);
		return solicitudRepository.save(solicitud);
	}
	
	@PostMapping("tarea/{id}")
	private List<Solicitud> getSolicitudesByTarea(@PathVariable int id,@RequestBody LinkedHashMap linkedHashMap) {
		return solicitudRepository.findByTarea_Id(id);
	}
	
}
