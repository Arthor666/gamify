package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gamificacion.demo.Models.Usuario;

public interface IUserRepository extends JpaRepository<Usuario, Integer>{
		
	@Query(value = "SELECT u.* FROM usuario u WHERE u.is_aviable = 1",nativeQuery = true)
	List<Usuario> findAviableUsers();
	List<Usuario>findByNombreContains(String nombre);
	List<Usuario>findByisAvailableAndNombreContains(boolean i,String nombre);
	Page<Usuario>findAllByOrderByIdAsc(Pageable p);
	Page<Usuario>findAllByOrderByIdDesc(Pageable p);
	List<Usuario> findByEquipos_Id(int id);
	List<Usuario> findByEquipos_Proyecto_Id(int id);
	List<Usuario> findByEtiquetadas_Id(int id);
	List<Usuario> findAllByOrderByIdDesc();
	Usuario findByCorreo(String userEmail);
	@Query(value = "SELECT u.* FROM usuario u INNER JOIN grupo_alumno ga ON ga.id_usuario = u.id WHERE u.nombre LIKE %?1% and ga.id_grupo = ?2 and u.id NOT IN(SELECT u.id FROM usuario u INNER JOIN equipo_alumno ea ON ea.id_usuario = u.id INNER JOIN equipo e ON e.id = ea.id_equipo WHERE e.id_grupo = ?2 and u.nombre LIKE %?1% )",nativeQuery = true)
	List<Usuario> findByNombreContainsAndGrupos_IdAndNotInEquipo(String n, int id);	
}
