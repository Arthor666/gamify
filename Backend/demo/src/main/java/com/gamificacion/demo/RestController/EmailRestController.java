package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Repository.IUserRepository;
import com.gamificacion.demo.Services.EmailService;

@RestController
@RequestMapping("correo")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EmailRestController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private IUserRepository userRepository;

	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	
	


	public EmailRestController(EmailService emailService, IUserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.emailService = emailService;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}





	@PostMapping("/correo")
	private ResponseEntity<LinkedHashMap> sendMailToRecoverCount(@RequestBody LinkedHashMap linkedHashMap){
		String email = (String) linkedHashMap.get("correo");
		Usuario user = userRepository.findByCorreo(email);
		if(user == null) {
			return ResponseEntity.notFound().build();	
		}
		Random random = new Random();
		String generatedString = random.ints(48, 122+ 1)
			      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			      .limit(10)
			      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			      .toString();
		user.setPassword(bCryptPasswordEncoder.encode(generatedString));
		userRepository.save(user);
		emailService.sendEmail(email, "Recupera tu cuenta en Saol", "Tu nueva contrasenia es: "+generatedString);
		return ResponseEntity.ok(linkedHashMap);
	}

}
