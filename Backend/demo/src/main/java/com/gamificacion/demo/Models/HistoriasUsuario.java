package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the historias_usuario database table.
 * 
 */
@Entity
@Table(name="historias_usuario")
@NamedQuery(name="HistoriasUsuario.findAll", query="SELECT h FROM HistoriasUsuario h")
public class HistoriasUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Lob
	private String descripcion;

	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;

	private String nombre;

	@Column(name="puntos_historia")
	private double puntosHistoria;

	//bi-directional many-to-one association to Proyecto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Equipo equipo;

	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="historiasUsuario")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;

	
	
	public HistoriasUsuario() {
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


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPuntosHistoria() {
		return this.puntosHistoria;
	}

	public void setPuntosHistoria(double puntosHistoria) {
		this.puntosHistoria = puntosHistoria;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setHistoriasUsuario(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setHistoriasUsuario(null);

		return tarea;
	}

}