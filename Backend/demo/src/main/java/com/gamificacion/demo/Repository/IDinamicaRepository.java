package com.gamificacion.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Dinamica;

public interface IDinamicaRepository extends JpaRepository<Dinamica, Long>{
	Dinamica findByTareas_Id(int id);
}
