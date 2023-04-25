package com.gamificacion.demo.Repository;

import java.lang.module.FindException;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gamificacion.demo.Models.Tarea;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Integer>{

	List<Tarea> findByAutor_Id(int id);

	List<Tarea>  findByEtiquetados_Id(int id);

	List<Tarea> findByEquipo_Id(int id);
	
	@Procedure("etiquetados_notificaciones")
	void crearnotificionEtiquetado(int idTarea,int idUsuario);

	@Modifying
	@Transactional
	@Query( nativeQuery = true ,value = "UPDATE tarea SET id_status = :sid WHERE id = :tid")
	void updateStatus(@Param("sid")int statusId,@Param("tid") int tareaId);

	

	List<Tarea> findByNombreContains(String nombre);

	List<Tarea> findByAutor_IdAndEquipo_IdOrderByFechaTentativaAsc(int idUsuario, int idEquipo);

	
	
	
	

}
