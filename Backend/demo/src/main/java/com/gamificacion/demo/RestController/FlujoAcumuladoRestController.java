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
import com.gamificacion.demo.Models.FlujoAcumulado;
import com.gamificacion.demo.Repository.IFlujoAcumuladoRepository;

@RestController
@RequestMapping("flujoAcumulado")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class FlujoAcumuladoRestController {
	
	@Autowired
	private IFlujoAcumuladoRepository flujoAcumuladoRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	public FlujoAcumuladoRestController(IFlujoAcumuladoRepository flujoAcumuladoRepository, ObjectMapper objectMapper) {
		super();
		this.flujoAcumuladoRepository = flujoAcumuladoRepository;
		this.objectMapper = objectMapper;
	}
	
	@PostMapping("/equipo")
	public List<FlujoAcumulado> getByEquipoIdAndStatusId(@RequestBody LinkedHashMap linkedHashMap){
		int idProyecto = (int) linkedHashMap.get("idProyecto");
		int idStatus = (int) linkedHashMap.get("idStatus");
		return flujoAcumuladoRepository.findByEquipo_IdAndStatus_Id(idProyecto,idStatus );
	}

}
