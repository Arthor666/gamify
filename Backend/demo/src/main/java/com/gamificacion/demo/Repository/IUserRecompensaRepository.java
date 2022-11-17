package com.gamificacion.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamificacion.demo.Models.Recompensa;
import com.gamificacion.demo.Models.UsuarioRecompensa;

public interface IUserRecompensaRepository extends JpaRepository<UsuarioRecompensa, Integer>{
	

}
