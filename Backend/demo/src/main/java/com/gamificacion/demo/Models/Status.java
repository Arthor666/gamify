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
	private int id;

	@Column(name="class_to_work")
	private String classToWork;

	private String nombre;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="status")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Solicitud> solicituds;

	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="status")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;

	//bi-directional many-to-one association to UsuarioRecompensa
	@OneToMany(mappedBy="status")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<UsuarioRecompensa> usuarioRecompensas;

	public Status() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassToWork() {
		return this.classToWork;
	}

	public void setClassToWork(String classToWork) {
		this.classToWork = classToWork;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setStatus(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setStatus(null);

		return solicitud;
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setStatus(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setStatus(null);

		return tarea;
	}

	public List<UsuarioRecompensa> getUsuarioRecompensas() {
		return usuarioRecompensas;
	}

	public void setUsuarioRecompensas(List<UsuarioRecompensa> usuarioRecompensas) {
		this.usuarioRecompensas = usuarioRecompensas;
	}

	

}