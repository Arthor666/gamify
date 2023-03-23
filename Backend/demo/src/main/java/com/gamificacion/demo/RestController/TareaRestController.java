package com.gamificacion.demo.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.Enums.ConstantsEnum;
import com.gamificacion.demo.Functions.Functions;
import com.gamificacion.demo.Models.Tarea;
import com.gamificacion.demo.Models.Usuario;
import com.gamificacion.demo.Repository.IStatusRepository;
import com.gamificacion.demo.Repository.ITareaRepository;
import com.gamificacion.demo.Services.FilesStorageService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("tarea")
public class TareaRestController {
	
	@Autowired
	private ITareaRepository tareaRepository;
	
	@Autowired
	private IStatusRepository statusRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	  FilesStorageService storageService;
	
	public TareaRestController(ITareaRepository tareaRepository,ObjectMapper objectMapper,IStatusRepository statusRepository,FilesStorageService storageService) {
		this.tareaRepository = tareaRepository;
		this.objectMapper = objectMapper;
		this.statusRepository = statusRepository;
		this.storageService = storageService;
	}
		
	@PutMapping("/tarea/status")
	public Tarea update(@RequestBody LinkedHashMap linkedHashMap) {
		Tarea tarea = objectMapper.convertValue(linkedHashMap, Tarea.class);
		tareaRepository.updateStatus(tarea.getStatus().getId(),tarea.getId());
		return tarea;
	}
	
	
	@PostMapping("/tarea")
	public Tarea saveTarea(@RequestBody LinkedHashMap linkedHashMap) {
		/*
		 * {			    
                "fechaTentativa":"2022-11-13T18:25:43.511Z",
			    "descripcion":"Tarea de prueba32",
			    "files":"",
			    "porcentajePenalizacion": "47",
			    "puntosRecompensa":"100",
			    "equipo":{
			        "id":1
			    },
			    "dinamica":{
			        "id":1
			    },
			    "status":{
			        "id":1
			    },
                "subequipos":[
                    {
                        "usuarios":[
                            {
                                "id":1
                            }
                        ]
                    },
                    {
                        "usuarios":[
                            {
                                "id":4
                            }
                        ]
                    }
                ]
                                                  
}
		 * 
		 * 
		 * */
				
		linkedHashMap.remove("signature");		
		Tarea tarea = objectMapper.convertValue(linkedHashMap, Tarea.class);		
		Tarea t =tareaRepository.save(tarea);
		for(Usuario u: t.getEtiquetados()) {
			tareaRepository.crearnotificionEtiquetado(t.getId(),u.getId());
		}		
		return t;
	}
	
	@PostMapping("/proyecto")
	private List<Tarea> getTareasByProyectoId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");
		return tareaRepository.findByProyecto_Id(id);
	}
		
	@PostMapping("/usuario")
	private List<Tarea> getTareasByUsuarioId(@RequestBody LinkedHashMap linkedHashMap){
		int id = (int) linkedHashMap.get("id");		
		List<Tarea> tList = tareaRepository.findByAutor_Id(id);
		tList.addAll(tareaRepository.findByEtiquetados_Id(id));
		return tList;
		
	}
		
	

}
