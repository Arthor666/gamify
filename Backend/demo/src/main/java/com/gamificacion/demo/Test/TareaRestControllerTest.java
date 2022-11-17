package com.gamificacion.demo.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationExtensionsKt;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamificacion.demo.DemoApplication;
import com.gamificacion.demo.Enums.ConstantsEnum;
import com.gamificacion.demo.Functions.Functions;
import com.gamificacion.demo.Models.Tarea;
import com.gamificacion.demo.Repository.IStatusRepository;
import com.gamificacion.demo.Repository.ITareaRepository;
import com.gamificacion.demo.RestController.TareaRestController;


public class TareaRestControllerTest {
	
	/*@Autowired
	private MockMvc mockMvc;*/
	
	@Test
	public void saveTareaTest() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		
	}
	
	
	@Test
	void updatePoints() {		
		double puntos = Functions.calculatePuntosRecompensa(100.00,47,new Timestamp(122,10,12,10,10,1,0));
		assertEquals(28.09, puntos);
	}
	
	@Test
	void testEnum() {	
		assertEquals(ConstantsEnum.STATUSFINALIZADO.toString(), "Finalizada");
		assertEquals(ConstantsEnum.STATUSPORCOBRAR.toString(), "Por Cobrar");
		assertEquals(ConstantsEnum.PATHROOT.toString(), "D:/gamify/uploads/");
	}
}
