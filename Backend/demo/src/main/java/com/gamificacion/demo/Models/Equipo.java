package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the equipo database table.
 * 
 */
@Entity
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name="fecha_creacion",insertable = false)	
	private Timestamp fechaCreacion;	

	@Column(name="is_active")
	private boolean isActive;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipo_empleado",joinColumns = @JoinColumn(name = "id_equipo"),inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Usuario> usuarios;
	
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="equipo")	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Proyecto> proyectos;

	//bi-directional many-to-one association to EquipoEmpleado	

	public Equipo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}