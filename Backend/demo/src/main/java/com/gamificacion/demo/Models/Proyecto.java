package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@NamedQuery(name="Proyecto.findAll", query="SELECT p FROM Proyecto p")
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Lob
	private String descripcion;

	@Column(name="fecha_creacion",insertable = false)	
	private Timestamp fechaCreacion;

	@Column(name="fecha_entrega")
	private Timestamp fechaEntrega;

	@Lob
	private String files;

	private String nombre;

	@Column(name="porcentaje_penalizacion")
	private int porcentajePenalizacion;
		
	@OneToMany(fetch = FetchType.LAZY,mappedBy="proyecto")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)	
	private List<Equipo> equipos;
	
	@ManyToOne
	@JoinColumn(name="id_profesor")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Usuario profesor;
		
	
	@Column(name="is_active")
	private boolean isActive;

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Proyecto() {
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

	public Timestamp getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getFiles() {
		return this.files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPorcentajePenalizacion() {
		return this.porcentajePenalizacion;
	}

	public void setPorcentajePenalizacion(int porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}



	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}
	
	
	
	

}