package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Proyecto;

public interface IProyectoRepository extends JpaRepository<Proyecto, Integer> {
	Proyecto findByEquipos_Id(int id);
	List<Proyecto>findByNombreContains(String nombre);
	Proyecto findById(int id);
	
	Page<Proyecto> findAllByOrderByIdAsc(Pageable pageable);

	Page<Proyecto> findAllByOrderByIdDesc(Pageable pageable);
	List<Proyecto> findByProfesor_Id(int id);
	
}
