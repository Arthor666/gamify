package com.gamificacion.demo.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Config.JwtUtils;
import com.gamificacion.demo.DTO.AutheticationRequestObject;
import com.gamificacion.demo.DTO.UsuarioDTO;
import com.gamificacion.demo.Repository.IUserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthenticationRestController {
	
	@Autowired	
	private final AuthenticationManager authenticationManager;
	@Autowired
	private final UserDetailsService userDetailsService;
	@Autowired
	private final JwtUtils jwtUtils;	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private IUserRepository usuIUserRepository;
	
	
	
	
	public AuthenticationRestController(AuthenticationManager authenticationManager,
			UserDetailsService userDetailsService, JwtUtils jwtUtils, IUserRepository userRepository) {
		
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;
	}




	@PostMapping("/auth")
	public ResponseEntity<UsuarioDTO> autenticar(@RequestBody AutheticationRequestObject autheticationRequestObject){
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(autheticationRequestObject.getCorreo(),autheticationRequestObject.getPassword()));
		final UserDetails user = userDetailsService.loadUserByUsername(autheticationRequestObject.getCorreo());
		if(user != null ) {			
			UsuarioDTO u = objectMapper.convertValue(usuIUserRepository.findByCorreo(user.getUsername()), UsuarioDTO.class);
			u.setToken(jwtUtils.generateToken(user));			
			return	ResponseEntity.status(HttpStatus.OK).body(u);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
	

}
