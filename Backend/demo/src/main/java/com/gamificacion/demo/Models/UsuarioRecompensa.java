package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario_recompensa database table.
 * 
 */
@Entity
@Table(name="usuario_recompensa")
@NamedQuery(name="UsuarioRecompensa.findAll", query="SELECT u FROM UsuarioRecompensa u")
public class UsuarioRecompensa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	//bi-directional many-to-one association to Recompensa
	@ManyToOne
	@JoinColumn(name="id_recompensa")
	private Recompensa recompensa;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public UsuarioRecompensa() {
	}


	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Recompensa getRecompensa() {
		return recompensa;
	}


	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	

}