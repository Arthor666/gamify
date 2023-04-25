package com.gamificacion.demo.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gamificacion.demo.Models.Grupo;

public interface IGrupoRepository extends JpaRepository<Grupo, Integer>{
	
	List<Grupo> findByProfesor_Id(int id);
	List<Grupo> findByAlumnos_Id(int id);	
	Grupo findByCodigoAcceso(String codigo);

	@Transactional
	@Query(nativeQuery = true,value="INSERT INTO grupo_alumno ga (ga.id_grupo,ga.id_alumno) VALUES (?1,?2)")
	void inscribirAlumno(int idGrupo, int idAlumno);

}
