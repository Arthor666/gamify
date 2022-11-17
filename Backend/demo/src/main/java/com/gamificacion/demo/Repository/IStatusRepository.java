package com.gamificacion.demo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gamificacion.demo.Models.Status;

public interface IStatusRepository extends JpaRepository<Status, Integer>  {
	
	List<Status> findByClassToWork(String classToWork);
	
	@Query(value = "SELECT s FROM Status s WHERE nombre = 'finalizada'")
	Status findTareaFinalizadaStatus();
	
	Status findByNombre(String nombre);
}
