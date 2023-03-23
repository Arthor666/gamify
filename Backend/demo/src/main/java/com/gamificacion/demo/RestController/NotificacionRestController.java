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
import com.gamificacion.demo.DTO.CountDTO;
import com.gamificacion.demo.Models.Notificacion;
import com.gamificacion.demo.Repository.INotificacionRepoitory;

@RestController
@RequestMapping("notificacion")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class NotificacionRestController {
	
	@Autowired
	private INotificacionRepoitory notificacionRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	public NotificacionRestController(INotificacionRepoitory notificacionRepository, ObjectMapper objectMapper) {
		super();
		this.notificacionRepository = notificacionRepository;
		this.objectMapper = objectMapper;
	}
	
	@PostMapping("/count")
	public CountDTO countByUsuarioId(@RequestBody LinkedHashMap linkedHashMap) {
		CountDTO c = new CountDTO();
		int id = (int) linkedHashMap.get("id");
		c.setCount(notificacionRepository.countByVistoAndUsuario_Id(id));
		return c;
	}
	
	@PostMapping("/user")
	public List<Notificacion> getByUsuarioId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return notificacionRepository.findByUsuario_Id(id);
	}

}
