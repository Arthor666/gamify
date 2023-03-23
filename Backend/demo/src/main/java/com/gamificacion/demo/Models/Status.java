package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-many association to Clases
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name="clase_status"
		, joinColumns={
			@JoinColumn(name="id_status")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_clase")
			}
		)
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Clases> clases;
	
	@OneToMany(mappedBy="status")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;
	
	@OneToMany(mappedBy="status")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Proyecto> proyectos;
	
	public Status() {
	}

	
	
	public List<Proyecto> getProyectos() {
		return proyectos;
	}



	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Clases> getClases() {
		return this.clases;
	}

	public void setClases(List<Clases> clases) {
		this.clases = clases;
	}

}