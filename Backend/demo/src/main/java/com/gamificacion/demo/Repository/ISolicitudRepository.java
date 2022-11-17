package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Solicitud;

public interface ISolicitudRepository extends JpaRepository<Solicitud, Long>{
	List<Solicitud> findByTarea_Id(int id);
}
