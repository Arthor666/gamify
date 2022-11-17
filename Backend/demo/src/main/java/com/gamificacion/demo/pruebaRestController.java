package com.gamificacion.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pruebaRestController {
	@GetMapping("/prueba")
	public prueba getPrueba() {
		return new prueba("Prueba 1", "Pls funciona");
	}
}
