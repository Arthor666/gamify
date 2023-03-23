package com.gamificacion.demo.RestController;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Models.Recompensa;
import com.gamificacion.demo.Repository.IRecompensaRepository;

@RestController
@RequestMapping("recompensa")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class RecompensaRestController {
	@Autowired
	private IRecompensaRepository recompensaRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	 private RecompensaRestController(IRecompensaRepository recompensaRepository) {
		 this.recompensaRepository = recompensaRepository; 
	 }
	 
	 @PostMapping("/recompensa")
	 private Recompensa saveRecompensa(@RequestBody LinkedHashMap linkedHashMap) {
		 /*
		  * {
				"nombre":"Dia de vacaciones",
    			"puntos":850.1,
    			"descripcion":"Es un simple dia de vacaciones para divertirse",
    			"signature":"wdojfsiodns654684dfg"
			}
		  * */
		 //linkedHashMap.remove("signature");
		 Recompensa recompensa = objectMapper.convertValue(linkedHashMap,Recompensa.class);
		 return recompensaRepository.save(recompensa);
		 
	 }
	 
	 @PostMapping("/all")
	 private List<Recompensa> getAll(@RequestBody LinkedHashMap linkedHashMap){		
		 return recompensaRepository.findAll();
	 }
	 @PostMapping("/nombre")
	 private List<Recompensa> getByNombreLike(@RequestBody LinkedHashMap linkedHashMap){
		 String nombre = (String) linkedHashMap.get("nombre");
		 return recompensaRepository.findByNombreContains(nombre);
	 }	 
	 @PostMapping("/user/{id}")
	 private List<Recompensa> getRecompensasByUser(@PathVariable int id,@RequestBody LinkedHashMap linkedHashMap){		 
		 return recompensaRepository.findByUsuarioRecompensas_Usuario_Id(id);
	 }
	 
}
