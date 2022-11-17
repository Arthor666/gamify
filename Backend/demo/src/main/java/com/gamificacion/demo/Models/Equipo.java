package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the equipo database table.
 * 
 */
@Entity
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name="fecha_creacion",insertable = false,updatable = false)
	private Timestamp fechaCreacion;

	@Column(name="is_active")
	private boolean isActive;

	private String nombre;

	@ManyToOne()
	@JoinColumn(name = "id_jefe",nullable = false)
	private Usuario jefe;
	
	//bi-directional many-to-many association to Usuario
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="equipo_empleado"
		, joinColumns={
			@JoinColumn(name="id_equipo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_usuario")
			}
		)
	private List<Usuario> usuarios;

	//bi-directional many-to-one association to Tarea
	@OneToMany(fetch = FetchType.LAZY,mappedBy="equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;

	public Equipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setEquipo(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setEquipo(null);

		return tarea;
	}

	public Usuario getJefe() {
		return jefe;
	}

	public void setJefe(Usuario jefe) {
		this.jefe = jefe;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
}