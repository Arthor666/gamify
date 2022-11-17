package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gamificacion.demo.Models.Usuario;

public interface IUserRepository extends JpaRepository<Usuario, Integer>{
	
	List<Usuario> findBySubequipos_Id(int id);

	@Query(value = "SELECT u.* FROM usuario u LEFT JOIN equipo_empleado ee ON u.id = ee.id_usuario LEFT JOIN equipo e ON  u.id = e.id_jefe WHERE u.id_rol = 1 GROUP BY u.id HAVING COUNT(u.id) < 2;",nativeQuery = true)
	List<Usuario> findAviableUsers();

}
