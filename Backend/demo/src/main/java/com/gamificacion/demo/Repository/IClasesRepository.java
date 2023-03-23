package com.gamificacion.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Clases;

public interface IClasesRepository extends JpaRepository<Clases, Integer>{
	Clases findByNombre(String n);
}
