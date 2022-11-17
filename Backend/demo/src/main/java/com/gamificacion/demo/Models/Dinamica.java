package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the dinamica database table.
 * 
 */
@Entity
@NamedQuery(name="Dinamica.findAll", query="SELECT d FROM Dinamica d")
public class Dinamica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Tarea
	@OneToMany(fetch = FetchType.LAZY,mappedBy="dinamica")
	@JsonIgnore
	private List<Tarea> tareas;

	public Dinamica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setDinamica(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setDinamica(null);

		return tarea;
	}

}