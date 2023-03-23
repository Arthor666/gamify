package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Recompensa;

public interface IRecompensaRepository extends JpaRepository<Recompensa, Integer>{
	List<Recompensa> findByUsuarioRecompensas_Usuario_Id(int id);	
	List<Recompensa> findByIdNotInAndPuntosLessThanEqual(List<Integer> ids,double puntos);
	List<Recompensa> findByNombreContains(String nombre);
}
