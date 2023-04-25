package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="codigo_acceso")
	private String codigoAcceso;

	@Column(name="fecha_creacion",insertable = false)
	private Timestamp fechaCreacion;

	private String nombre;

	//bi-directional many-to-one association to Equipo
	@OneToMany(fetch = FetchType.LAZY,mappedBy="grupo")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Equipo> equipos;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(
			  name = "grupo_alumno", 
			  joinColumns = @JoinColumn(name = "id_grupo"), 
			  inverseJoinColumns = @JoinColumn(name = "id_usuario"))
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Usuario> alumnos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_profesor")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario profesor;	

	public Grupo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigoAcceso() {
		return this.codigoAcceso;
	}

	public void setCodigoAcceso(String codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Equipo> getEquipos() {
		return this.equipos;
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

	public List<Usuario> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Usuario> alumnos) {
		this.alumnos= alumnos;
	}
	
	
	
	public void generateUniqueCode() {
		String aux = "" ;
		Calendar now = Calendar. getInstance();
		aux = now.get(Calendar.YEAR)%100+""+now.get(Calendar.MONTH)+""+now.get(Calendar.DAY_OF_MONTH)+""+now.get(Calendar.HOUR)+""+now.get(Calendar.MINUTE);
		aux = Integer.toHexString(Integer.parseInt(aux));
		this.codigoAcceso = this.nombre.substring(0,3)+aux.toUpperCase();
	}

}