package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;

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
	
	@PostMapping("/all")
	public List<Usuario> getAllUser() {
		return userRepository.findAllByOrderByIdDesc();
	}
	
	@PostMapping("/id")
	public Usuario getById(@RequestBody LinkedHashMap linkedHashMap) {
		int id = (int) linkedHashMap.get("id");
		return userRepository.findById(id).get();
	}
	
	@PostMapping("/user")
	public Usuario saveUser(@RequestBody LinkedHashMap linkedHashMap) {
		Usuario user = objectMapper.convertValue(linkedHashMap,Usuario.class);
		/* La data tiene que viajar como un JSON
		 * {
		 	"id": 1 //Solo si se quiere actualizar, si no se necesita actualizar este parametro no debe existir
    		"correo":"arturosassa@asd.csd",
    		"nombre":"Arturo",
    		"nombreUsuario":"Arthor666",
    		"password":"SecretKey123",
    		"rol":{ //parametros del rol, puede ser solamente el id }
    		"isActive" : 1
    		"puntaje": 0.0,
    		"equipos": null,    		    		
    		
		   }
		 * */
		return userRepository.save(user);
	}
	
	@PostMapping("/available")
	public List<Usuario> getAviableUsers(@RequestBody LinkedHashMap linkedHashMap){
		//Revisar el signature
		
		return userRepository.findAviableUsers();
	}
	
	@PostMapping("/name")
	public List<Usuario> getUserByName(@RequestBody LinkedHashMap linkedHashMap){
		//Revisar el signature
		String n = (String) linkedHashMap.get("nombre");
		return userRepository.findByNombreContains(n);
	}
	
	@PostMapping("/nameAvailable")
	public List<Usuario> getUserByNameAndAviable(@RequestBody LinkedHashMap linkedHashMap){
		//Revisar el signature
		String n = (String) linkedHashMap.get("nombre");
		return userRepository.findByisAvailableAndNombreContains(true,n);
	}
	
	@PostMapping("/equipo")
	public List<Usuario> getUserByEquipoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return userRepository.findByEquipos_Id(id);
	}
	
	@PostMapping("/proyecto")
	public List<Usuario> getUserByProyectoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return userRepository.findByEquipos_Proyectos_Id(id);
	}
	
	@PostMapping("/etiquetado")
	public List<Usuario> getUserByEtiquetado(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return userRepository.findByEtiquetadas_Id(id);
	}
	
	@PostMapping("/userAdmin")
	public Page<Usuario> getAll(@RequestBody LinkedHashMap linkedHashMap){
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
				return userRepository.findAllByOrderByIdAsc(pageable);		
			}
		}		
		return userRepository.findAllByOrderByIdDesc(pageable);
	}
	
}
