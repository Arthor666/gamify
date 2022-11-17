package com.gamificacion.demo.DTO;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gamificacion.demo.Models.Dinamica;
import com.gamificacion.demo.Models.Equipo;
import com.gamificacion.demo.Models.Status;
import com.gamificacion.demo.Models.Subequipo;

public class TareaDTO {

	private int id;

	private String descripcion;

	private Timestamp fechaCreacion;

	private Timestamp fechaTentativa;

	private String files;

	private int porcentajePenalizacion;

	private double puntosRecompensa;

	private List<Subequipo> subequipos;

	private Equipo equipo;

	private Dinamica dinamica;

	private Status status;

	private Subequipo subequipo;

	public TareaDTO() {
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

	/*public Subequipo addSubequipo(Subequipo subequipo) {
		getSubequipos().add(subequipo);
		subequipo.setTarea(this);

		return subequipo;
	}*/

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
