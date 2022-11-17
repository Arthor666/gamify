package com.gamificacion.demo.RestController;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamificacion.demo.Models.Status;
import com.gamificacion.demo.Repository.IStatusRepository;

@RestController
@RequestMapping("status")
public class StatusRestController {
	@Autowired
	private IStatusRepository statusRepository;
	
	StatusRestController(IStatusRepository  statusRepository) {
		this.statusRepository = statusRepository;
	}
		

	@GetMapping("/status")
	private List<Status> getAllStatus() {
		return statusRepository.findAll();
	}
	
	@PostMapping("/status/{classToWork}")
	private List<Status> getStatusByClassToWork(@PathVariable String classToWork,@RequestBody LinkedHashMap linkedHashMap){
		return statusRepository.findByClassToWork(classToWork);
	}
	
	//@GetMapping("/get")
	
}
