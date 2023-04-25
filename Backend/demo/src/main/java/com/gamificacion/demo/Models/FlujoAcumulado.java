package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the flujo_acumulado database table.
 * 
 */
@Entity
@Table(name="flujo_acumulado")
@NamedQuery(name="FlujoAcumulado.findAll", query="SELECT f FROM FlujoAcumulado f")
public class FlujoAcumulado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(name="fecha_guardado")
	private Timestamp fechaGuardado;

	@ManyToOne
	@JoinColumn(name="id_status")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Status status;

	@Column(name="num_tareas")
	private int numTareas;

	//bi-directional many-to-one association to Proyecto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Equipo equipo;

	
	
	public FlujoAcumulado() {
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Timestamp getFechaGuardado() {
		return this.fechaGuardado;
	}

	public void setFechaGuardado(Timestamp fechaGuardado) {
		this.fechaGuardado = fechaGuardado;
	}

	


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public int getNumTareas() {
		return this.numTareas;
	}

	public void setNumTareas(int numTareas) {
		this.numTareas = numTareas;
	}


	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	

}