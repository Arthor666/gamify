package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import com.gamificacion.demo.Models.Equipo;

public interface IEquipoRepository extends JpaRepository<Equipo, Integer>{
	Page<Equipo> findAll(Pageable pagable);	
	Page<Equipo> findAllByOrderByIdAsc(Pageable pagable);
	Page<Equipo> findAllByOrderByIdDesc(Pageable pagable);
	List<Equipo> findByUsuarios_Id(int id);
	List<Equipo> findByNombreContains(String nombre);
	Equipo findByProyectos_Id(int id);
	@Procedure("update_available_user")
	void updateAvalableUser(int id);
	@Query(nativeQuery = true,value = "SELECT count(*) FROM proyecto WHERE id_equipo = ?1 AND id_status != (SELECT id FROM status WHERE nombre='Finalizado');")
	long countProyectosNoFinalizados(int id);	
}
