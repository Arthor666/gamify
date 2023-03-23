package com.gamificacion.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Recompensa;
import com.gamificacion.demo.Models.UsuarioRecompensa;

public interface IUserRecompensaRepository extends JpaRepository<UsuarioRecompensa, Integer>{

	List<UsuarioRecompensa>findByUsuario_Id(int id);

	List<UsuarioRecompensa> findByRecompensa_Nombre(String n);
	

}
