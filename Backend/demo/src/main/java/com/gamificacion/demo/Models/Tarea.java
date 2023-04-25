package com.gamificacion.demo.Models;


import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
@DynamicUpdate
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Lob
	private String descripcion;
	
	private String nombre;

	@Column(name="fecha_creacion",insertable = false)
	private Timestamp fechaCreacion;

	@Column(name="fecha_tentativa")
	private Timestamp fechaTentativa;

	@Lob
	private String files;

	@ManyToOne
	@JoinColumn(name="id_autor")
	private Usuario autor;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "etiquetados",joinColumns = @JoinColumn(name = "id_tarea"),inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Usuario>etiquetados;
	
	@ManyToOne
	@JoinColumn(name="id_status")
	private Status status;
		

	//bi-directional many-to-one association to Proyecto
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Equipo equipo;

	//bi-directional many-to-one association to HistoriasUsuario
	@ManyToOne
	@JoinColumn(name="id_historia")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)	
	private HistoriasUsuario historiasUsuario;


	public Tarea() {
	}
	
	

	public List<Usuario> getEtiquetados() {
		return etiquetados;
	}



	public void setEtiquetados(List<Usuario> etiquetados) {
		this.etiquetados = etiquetados;
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

	public Usuario getAutor() {
		return this.autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	

	public Equipo getEquipo() {
		return equipo;
	}



	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}



	public HistoriasUsuario getHistoriasUsuario() {
		return this.historiasUsuario;
	}

	public void setHistoriasUsuario(HistoriasUsuario historiasUsuario) {
		this.historiasUsuario = historiasUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}