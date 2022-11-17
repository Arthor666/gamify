package com.gamificacion.demo.Repository;

import java.lang.module.FindException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamificacion.demo.Models.Tarea;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Integer>{
	

	public List<Tarea> findBySubequipos_Usuarios_Id(int i);
	

}
