package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Models.UsuarioRecompensa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Repository.IUserRecompensaRepository;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("usuarioRecompensa")
public class UsuarioRecompensaRestController {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private IUserRecompensaRepository userRecompensaRepository;

	public UsuarioRecompensaRestController(ObjectMapper objectMapper,
			IUserRecompensaRepository userRecompensaRepository) {
		super();
		this.objectMapper = objectMapper;
		this.userRecompensaRepository = userRecompensaRepository;
	}
	
	@PostMapping("/usuarioRecompensa")
	public UsuarioRecompensa save(@RequestBody LinkedHashMap linkedHashMap){
		UsuarioRecompensa u =  objectMapper.convertValue(linkedHashMap,UsuarioRecompensa.class);
		return userRecompensaRepository.save(u);
	}
	
	@PostMapping("/usuario")
	public List<UsuarioRecompensa> getAllByUsuarioId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int)linkedHashMap.get("id");
		return userRecompensaRepository.findByUsuario_Id(id);
	}
	
	@PostMapping("/nombre")
	public List<UsuarioRecompensa> getByNombreLike(@RequestBody LinkedHashMap linkedHashMap){
		String n = (String)linkedHashMap.get("nombre");
		return userRecompensaRepository.findByRecompensa_Nombre(n);
	}
	
	@PostMapping("/all")
	public List<UsuarioRecompensa> getAll(@RequestBody LinkedHashMap linkedHashMap){		
		return userRecompensaRepository.findAll();
	}

}
