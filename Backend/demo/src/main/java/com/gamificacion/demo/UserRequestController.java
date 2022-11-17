package com.gamificacion.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class UserRequestController {
	
	@GetMapping("/getUser")
	ResponseEntity<String> home() {
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }
}
