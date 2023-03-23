package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	List<Usuario> findByEquipos_Proyectos_Id(int id);
	List<Usuario> findByEtiquetadas_Id(int id);
	List<Usuario> findAllByOrderByIdDesc();
}
