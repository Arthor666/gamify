package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the recompensa database table.
 * 
 */
@Entity
@NamedQuery(name="Recompensa.findAll", query="SELECT r FROM Recompensa r")
public class Recompensa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Lob
	private String descripcion;

	private String nombre;

	private double puntos;

	//bi-directional many-to-one association to UsuarioRecompensa
	@OneToMany(mappedBy="recompensa")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<UsuarioRecompensa> usuarioRecompensas;

	public Recompensa() {
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

	public double getPuntos() {
		return this.puntos;
	}

	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}

	public List<UsuarioRecompensa> getUsuarioRecompensas() {
		return usuarioRecompensas;
	}

	public void setUsuarioRecompensas(List<UsuarioRecompensa> usuarioRecompensas) {
		this.usuarioRecompensas = usuarioRecompensas;
	}

}