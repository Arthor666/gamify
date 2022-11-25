package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Repository.IUserRepository;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("user")
public class UserRestController {
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	public UserRestController(IUserRepository userRepository,ObjectMapper objectMapper) {
		this.userRepository = userRepository;
		this.objectMapper = objectMapper;
	}
	
	@GetMapping("/getAll")
	public List<Usuario> getAllUser() {
		return userRepository.findAll();
	}
	
	@PostMapping("/user")
	public Usuario saveUser(@RequestBody LinkedHashMap linkedHashMap) {
		Usuario user = objectMapper.convertValue(linkedHashMap,Usuario.class);
		/* La data tiene que viajar como un JSON
		 * {
		 	"id": 1 //Solo si se quiere actualizar, si no se necesita actualizar, este parametro no debe existir
    		"correo":"arturosassa@asd.csd",
    		"nombre":"Arturo",
    		"nombreUsuario":"Arthor666",
    		"password":"SecretKey123",
    		"rol":{ //parametros del rol, puede ser solamente el id }
    		"isActive" : 1
    		"puntaje": 0.0,
    		"equipos1": null,
    		"equipos2": null,
    		"solicituds": null,
    		"subequipos": null,
    		
		   }
		 * */
		return userRepository.save(user);
	}
	
	@PostMapping("/available")
	public List<Usuario> getAviableUsers(@RequestBody LinkedHashMap linkedHashMap){
		//Revisar el signature
		
		return userRepository.findAviableUsers();
	}
	
	
	
}
