package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the subequipo database table.
 * 
 */
@Entity
@NamedQuery(name="Subequipo.findAll", query="SELECT s FROM Subequipo s")
public class Subequipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	//bi-directional many-to-one association to Tarea
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tarea")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JsonBackReference
	private Tarea tarea;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="subequipo_usuario"
		, joinColumns={
			@JoinColumn(name="id_subequipo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_usuario")
			}
		)
	private List<Usuario> usuarios;

	public Subequipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}