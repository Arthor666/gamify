package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Proyecto;
import com.gamificacion.demo.Models.Quemado;

public interface IQuemadoRepository extends JpaRepository<Quemado, Integer> {

	List<Quemado> findByEquipo_Id(int id );

}
