package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gamificacion.demo.Models.Notificacion;


public interface INotificacionRepoitory extends JpaRepository<Notificacion, Integer>{

	@Query(nativeQuery = true,value = "SELECT count(*) FROM notificacion WHERE visto = 0  AND id_usuario = ?1")
	long countByVistoAndUsuario_Id(int id);

	List<Notificacion> findByUsuario_Id(int id);

}
