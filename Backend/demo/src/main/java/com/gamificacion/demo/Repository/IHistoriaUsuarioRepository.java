package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gamificacion.demo.DTO.PuntosHistoriaDTO;
import com.gamificacion.demo.Models.HistoriasUsuario;


public interface IHistoriaUsuarioRepository extends JpaRepository<HistoriasUsuario, Integer>{

	List<HistoriasUsuario> findByEquipo_Id(int id);

	List<HistoriasUsuario> findByNombreContains(String n);

	HistoriasUsuario findByTareas_Id(int id);

	@Query(value = "SELECT sum(puntos_historia) FROM historias_usuario WHERE id_equipo = ?1",nativeQuery = true)
	double conteoDePuntos(int id);

}
