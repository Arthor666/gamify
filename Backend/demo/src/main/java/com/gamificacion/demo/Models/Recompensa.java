package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.skyscreamer.jsonassert.JSONCompareMode;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Lob
	private String descripcion;

	private String nombre;		

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "recompensa")
	@JsonProperty(access =  JsonProperty.Access.WRITE_ONLY)
	private List<Equipo> equipos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profesor")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Usuario profesor;
	
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
	
	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
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
	
	
	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

}