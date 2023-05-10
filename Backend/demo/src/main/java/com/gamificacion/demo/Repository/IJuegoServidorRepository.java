package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.gamificacion.demo.Models.JuegoServidor;

public interface IJuegoServidorRepository extends JpaRepository<JuegoServidor, Integer>{

	List<JuegoServidor> findByEquipo_Id(int id);
	
	
}
