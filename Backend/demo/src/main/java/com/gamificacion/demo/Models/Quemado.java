package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the quemado database table.
 * 
 */
@Entity
@NamedQuery(name="Quemado.findAll", query="SELECT q FROM Quemado q")
public class Quemado implements Serializable {
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(name="fecha_guardado")
	private Timestamp fechaGuardado;

	@Column(name="puntos_quemado")
	private double puntosQuemado;

	//bi-directional many-to-one association to Proyecto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Equipo equipo;

	
	public Quemado() {
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

	public double getPuntosQuemado() {
		return this.puntosQuemado;
	}

	public void setPuntosQuemado(double puntosQuemado) {
		this.puntosQuemado = puntosQuemado;
	}


	public Equipo getEquipo() {
		return equipo;
	}


	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	
}