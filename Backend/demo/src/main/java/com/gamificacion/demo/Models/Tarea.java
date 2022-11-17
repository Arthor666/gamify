package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Lob
	private String descripcion;

	@Column(name="fecha_creacion",insertable = false,updatable = false)
	private Timestamp fechaCreacion;

	@Column(name="fecha_tentativa")
	private Timestamp fechaTentativa;

	@Lob
	private String files;

	@Column(name="porcentaje_penalizacion")
	private int porcentajePenalizacion;

	@Column(name="puntos_recompensa")
	private double puntosRecompensa;

	//bi-directional many-to-one association to Subequipo
	@OneToMany(mappedBy="tarea",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","tareas"})
	private List<Subequipo> subequipos;

	//bi-directional many-to-one association to Equipo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","tareas"})
	private Equipo equipo;

	//bi-directional many-to-one association to Dinamica
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_dinamica")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","tareas"})
	private Dinamica dinamica;

	//bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="id_status")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","tareas"})
	private Status status;

	//bi-directional many-to-one association to Subequipo
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_subequipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","tareas"})
	private Subequipo subequipo;

	public Tarea() {
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

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaTentativa() {
		return this.fechaTentativa;
	}

	public void setFechaTentativa(Timestamp fechaTentativa) {
		this.fechaTentativa = fechaTentativa;
	}

	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getPorcentajePenalizacion() {
		return this.porcentajePenalizacion;
	}

	public void setPorcentajePenalizacion(int porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	public double getPuntosRecompensa() {
		return this.puntosRecompensa;
	}

	public void setPuntosRecompensa(double puntosRecompensa) {
		this.puntosRecompensa = puntosRecompensa;
	}

	public List<Subequipo> getSubequipos() {
		return this.subequipos;
	}

	public void setSubequipos(List<Subequipo> subequipos) {
		this.subequipos = subequipos;
	}

	public Subequipo addSubequipo(Subequipo subequipo) {
		getSubequipos().add(subequipo);
		subequipo.setTarea(this);

		return subequipo;
	}

	public Subequipo removeSubequipo(Subequipo subequipo) {
		getSubequipos().remove(subequipo);
		subequipo.setTarea(null);

		return subequipo;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Dinamica getDinamica() {
		return this.dinamica;
	}

	public void setDinamica(Dinamica dinamica) {
		this.dinamica = dinamica;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Subequipo getSubequipo() {
		return this.subequipo;
	}

	public void setSubequipo(Subequipo subequipo) {
		this.subequipo = subequipo;
	}

}