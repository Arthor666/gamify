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
	
	private Double calificacion;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "equipo_alumno ",joinColumns = @JoinColumn(name = "id_equipo"),inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Usuario> usuarios;
	
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name="id_proyecto")
	private Proyecto proyecto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_grupo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Grupo grupo;
	
	//bi-directional many-to-one association to FlujoAcumulado
	@OneToMany(mappedBy="equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<FlujoAcumulado> flujoAcumulados;

	//bi-directional many-to-one association to HistoriasUsuario
	@OneToMany(mappedBy="equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<HistoriasUsuario> historiasUsuarios;

	//bi-directional many-to-one association to Quemado
	@OneToMany(mappedBy="equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Quemado> quemados;

	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name="id_recompensa")
	private Recompensa recompensa;
	
	@OneToMany(mappedBy = "equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Status> status;

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
	
	

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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

	public List<FlujoAcumulado> getFlujoAcumulados() {
		return flujoAcumulados;
	}

	public void setFlujoAcumulados(List<FlujoAcumulado> flujoAcumulados) {
		this.flujoAcumulados = flujoAcumulados;
	}

	public List<HistoriasUsuario> getHistoriasUsuarios() {
		return historiasUsuarios;
	}

	public void setHistoriasUsuarios(List<HistoriasUsuario> historiasUsuarios) {
		this.historiasUsuarios = historiasUsuarios;
	}

	public List<Quemado> getQuemados() {
		return quemados;
	}

	public void setQuemados(List<Quemado> quemados) {
		this.quemados = quemados;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Recompensa getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Recompensa recompensa) {
		this.recompensa = recompensa;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
		this.calificacion = calificacion;
	}
	
	

}