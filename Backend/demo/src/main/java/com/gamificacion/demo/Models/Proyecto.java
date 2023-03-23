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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name="id_equipo")
	private Equipo equipo;

	@Column(name="puntos_recompensa")
	private double puntosRecompensa;

	//bi-directional many-to-one association to FlujoAcumulado
	@OneToMany(mappedBy="proyecto")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<FlujoAcumulado> flujoAcumulados;

	//bi-directional many-to-one association to HistoriasUsuario
	@OneToMany(mappedBy="proyecto")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<HistoriasUsuario> historiasUsuarios;

	//bi-directional many-to-one association to Quemado
	@OneToMany(mappedBy="proyecto")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Quemado> quemados;

	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="proyecto")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name="id_status")
	private Status status;
	
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

	public double getPuntosRecompensa() {
		return this.puntosRecompensa;
	}

	public void setPuntosRecompensa(double puntosRecompensa) {
		this.puntosRecompensa = puntosRecompensa;
	}

	public List<FlujoAcumulado> getFlujoAcumulados() {
		return this.flujoAcumulados;
	}

	public void setFlujoAcumulados(List<FlujoAcumulado> flujoAcumulados) {
		this.flujoAcumulados = flujoAcumulados;
	}

	public FlujoAcumulado addFlujoAcumulado(FlujoAcumulado flujoAcumulado) {
		getFlujoAcumulados().add(flujoAcumulado);
		flujoAcumulado.setProyecto(this);

		return flujoAcumulado;
	}

	public FlujoAcumulado removeFlujoAcumulado(FlujoAcumulado flujoAcumulado) {
		getFlujoAcumulados().remove(flujoAcumulado);
		flujoAcumulado.setProyecto(null);

		return flujoAcumulado;
	}

	public List<HistoriasUsuario> getHistoriasUsuarios() {
		return this.historiasUsuarios;
	}

	public void setHistoriasUsuarios(List<HistoriasUsuario> historiasUsuarios) {
		this.historiasUsuarios = historiasUsuarios;
	}

	public HistoriasUsuario addHistoriasUsuario(HistoriasUsuario historiasUsuario) {
		getHistoriasUsuarios().add(historiasUsuario);
		historiasUsuario.setProyecto(this);

		return historiasUsuario;
	}

	public HistoriasUsuario removeHistoriasUsuario(HistoriasUsuario historiasUsuario) {
		getHistoriasUsuarios().remove(historiasUsuario);
		historiasUsuario.setProyecto(null);

		return historiasUsuario;
	}

	public List<Quemado> getQuemados() {
		return this.quemados;
	}

	public void setQuemados(List<Quemado> quemados) {
		this.quemados = quemados;
	}

	public Quemado addQuemado(Quemado quemado) {
		getQuemados().add(quemado);
		quemado.setProyecto(this);

		return quemado;
	}

	public Quemado removeQuemado(Quemado quemado) {
		getQuemados().remove(quemado);
		quemado.setProyecto(null);

		return quemado;
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setProyecto(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setProyecto(null);

		return tarea;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	

}