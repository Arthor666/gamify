package com.gamificacion.demo.Models;

import java.io.Serializable;
import java.sql.Timestamp;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="id_recompensa")
	private Recompensa recompensa;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	@Column(name="fecha",insertable = false)	
	private Timestamp fecha;	

	
	
	public Timestamp getFecha() {
		return fecha;
	}

	public void setFechaCreacion(Timestamp fecha) {
		this.fecha = fecha;
	}

	public UsuarioRecompensa() {
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}