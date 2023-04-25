package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.FlujoAcumulado;

public interface IFlujoAcumuladoRepository extends JpaRepository<FlujoAcumulado, Integer>{

	

	List<FlujoAcumulado> findByEquipo_IdAndStatus_Id(int idProyecto,int idStatus);
	
	

}
