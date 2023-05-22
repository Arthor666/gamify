package com.gamificacion.demo.RestController;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 	
	@Autowired	
	private final AuthenticationManager authenticationManager;
	@Autowired
	private final UserDetailsService userDetailsService;
	
	
	
	public UserRestController(IUserRepository userRepository, ObjectMapper objectMapper,
			BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService) {
		super();
		this.userRepository = userRepository;
		this.objectMapper = objectMapper;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
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
	
	@PostMapping("/name/grupo")
	public List<Usuario> getUserByNameAndGrupoIdAndNotInEquipo(@RequestBody LinkedHashMap linkedHashMap) {
		String n = (String) linkedHashMap.get("name");
		int id = (int) linkedHashMap.get("id");		
		return userRepository.findByNombreContainsAndGrupos_IdAndNotInEquipo(n,id);
	}
	
	@PutMapping("/usuario")
	public ResponseEntity<Usuario> update(@RequestBody LinkedHashMap linkedHashMap) {
		Usuario usuarioRecibido = objectMapper.convertValue(linkedHashMap.get("usuario"),Usuario.class);
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioRecibido.getCorreo(),(String) linkedHashMap.get("currentPassword")));
		final UserDetails user = userDetailsService.loadUserByUsername(usuarioRecibido.getCorreo());
		if(user == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(usuarioRecibido);
		}
		String newPassword = (String) linkedHashMap.get("newPassword") ;
		Usuario usuarioDB = userRepository.findById(usuarioRecibido.getId()).get();
		if(newPassword != null) {
			usuarioRecibido.setPassword(bCryptPasswordEncoder.encode(newPassword));
			usuarioDB.setPassword(usuarioRecibido.getPassword());
		}		
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
		usuarioDB.setNombre(usuarioRecibido.getNombre());
		usuarioDB.setCorreo(usuarioRecibido.getCorreo());
		usuarioDB.setNombreUsuario(usuarioRecibido.getNombreUsuario());		
		usuarioDB.setRol(usuarioRecibido.getRol());		
		userRepository.save(usuarioDB);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioDB);
	}
	
	
	
	@PostMapping("/user")
	public ResponseEntity<Usuario> saveUser(@RequestBody LinkedHashMap linkedHashMap) {
		Usuario user = objectMapper.convertValue(linkedHashMap,Usuario.class);
		Usuario usuarioExists =  userRepository.findByCorreo(user.getCorreo());
		if(usuarioExists != null) {
			return new ResponseEntity<Usuario>(user ,HttpStatus.CONFLICT);
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
		return new ResponseEntity<Usuario>(user,HttpStatus.OK);
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
		return userRepository.findByEquipos_Proyecto_Id(id);
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
