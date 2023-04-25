package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the notificacion database table.
 * 
 */
@Entity
@NamedQuery(name="Notificacion.findAll", query="SELECT n FROM Notificacion n")
public class Notificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean visto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Usuario usuario;

	//bi-directional many-to-one association to Recompensa
	@ManyToOne
	@JoinColumn(name="id_recompensa")
	private Recompensa recompensa;

	//bi-directional many-to-one association to Tarea
	@ManyToOne
	@JoinColumn(name="id_tarea")
	private Tarea tarea;

	public Notificacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getVisto() {
		return this.visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Recompensa getRecompensa() {
		return this.recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

}