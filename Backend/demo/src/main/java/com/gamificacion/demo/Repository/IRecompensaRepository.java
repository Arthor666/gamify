package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Recompensa;

public interface IRecompensaRepository extends JpaRepository<Recompensa, Integer>{	
	List<Recompensa> findByNombreContains(String nombre);
	Recompensa findByEquipos_Id(int id);
	
	List<Recompensa> findByProfesor_Id(int id);
	List<Recompensa> findByProfesorIsNull();
}
