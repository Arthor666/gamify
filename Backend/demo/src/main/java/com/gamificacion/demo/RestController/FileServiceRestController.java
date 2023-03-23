package com.gamificacion.demo.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gamificacion.demo.Services.FilesStorageService;

@RestController
@RequestMapping("file")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class FileServiceRestController {
	
	@Autowired
	  FilesStorageService storageService;
	
	
	
	public FileServiceRestController(FilesStorageService storageService) {
		super();
		this.storageService = storageService;
	}



	@PostMapping("/file")
	private ResponseEntity<String> saveFiles(@RequestParam("files") MultipartFile[] files){
		//Recibir el signature como param

	      Arrays.asList(files).stream().forEach(file -> {
	        storageService.save(file);
	      });
	      return new ResponseEntity<String>(HttpStatus.OK);
	      
	}

	
	@PostMapping("/")
	private ResponseEntity<Resource> getTareaFiles(@RequestBody LinkedHashMap linkedHashMap){
		//Revisar Firma
		String fName = (String) linkedHashMap.get("nombre");
		Resource file = storageService.load(fName);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, file.getFilename() );
		return new ResponseEntity<Resource>(file,httpHeaders,HttpStatus.OK);
		
	}
}
