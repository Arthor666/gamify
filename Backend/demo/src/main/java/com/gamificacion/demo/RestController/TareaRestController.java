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
		if(tarea.getId()==0) {						
			return tareaRepository.save(tarea);
		}
		Tarea tareaDesactualizada = tareaRepository.findById(tarea.getId()).get();		
		int endedTaskIdStatus = statusRepository.findByNombre(ConstantsEnum.STATUSFINALIZADO.toString()).getId();
		if(tareaDesactualizada.getStatus().getId() == endedTaskIdStatus) {
			return tareaDesactualizada; //Ya no se debe de actualizar porque la tarea ya esta terminada
		}
		if(tarea.getId() != 0 && tarea.getStatus().getId() == endedTaskIdStatus && tarea.getSubequipo() != null) {									
				Functions.updateUserPoints(tarea);		
		}
		return tareaRepository.save(tarea);
	}
		
	@PostMapping("/usuario/{id}")
	private List<Tarea> getTareasByUsuarioId(@PathVariable int id,@RequestBody LinkedHashMap linkedHashMap){
		return tareaRepository.findBySubequipos_Usuarios_Id(id);
		
	}
	
	@PostMapping("/files")
	private ResponseEntity<String> saveFiles(@RequestParam("files") MultipartFile[] files){
		//Recibir el signature como param

	      Arrays.asList(files).stream().forEach(file -> {
	        storageService.save(file);
	      });
	      return new ResponseEntity<String>("Ok",HttpStatus.OK);
	      
	}
	
	@PostMapping("/files/{filename:.+}")
	private ResponseEntity<Resource> getTareaFiles(@PathVariable String filename,@RequestBody LinkedHashMap linkedHashMap){
		//Revisar Firma
		Resource file = storageService.load(filename);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, file.getFilename() );
		return new ResponseEntity<Resource>(file,httpHeaders,HttpStatus.OK);
		
	}

}
