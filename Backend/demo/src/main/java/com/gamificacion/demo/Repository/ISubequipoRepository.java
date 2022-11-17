package com.gamificacion.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Subequipo;
import com.gamificacion.demo.Models.Tarea;

public interface ISubequipoRepository extends JpaRepository<Subequipo, Long>{
	Subequipo findByTarea_Id(int id);
}
