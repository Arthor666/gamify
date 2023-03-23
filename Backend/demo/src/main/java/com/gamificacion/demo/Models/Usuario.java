package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	private String correo;

	@Column(name="is_active")
	private boolean isActive;

	@Column(name="is_available")
	private boolean isAvailable;

	private String nombre;

	@Column(name="nombre_usuario")
	private String nombreUsuario;

	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "usuarios")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Equipo>equipos;
	
	@OneToMany(mappedBy = "autor")	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> tareas;
	
	@ManyToMany(mappedBy = "etiquetados")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Tarea> etiquetadas;
	

	private double puntaje;
	
	@OneToMany(mappedBy = "usuario")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Notificacion> notificaciones;


	//bi-directional many-to-one association to Rol
	@ManyToOne	
	@JoinColumn(name="id_rol")
	private Rol rol;

	//bi-directional many-to-one association to UsuarioRecompensa
	@OneToMany(mappedBy="usuario")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<UsuarioRecompensa> usuarioRecompensas;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}


	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<UsuarioRecompensa> getUsuarioRecompensas() {
		return this.usuarioRecompensas;
	}

	public void setUsuarioRecompensas(List<UsuarioRecompensa> usuarioRecompensas) {
		this.usuarioRecompensas = usuarioRecompensas;
	}

	public UsuarioRecompensa addUsuarioRecompensa(UsuarioRecompensa usuarioRecompensa) {
		getUsuarioRecompensas().add(usuarioRecompensa);
		usuarioRecompensa.setUsuario(this);

		return usuarioRecompensa;
	}
	
	

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public UsuarioRecompensa removeUsuarioRecompensa(UsuarioRecompensa usuarioRecompensa) {
		getUsuarioRecompensas().remove(usuarioRecompensa);
		usuarioRecompensa.setUsuario(null);

		return usuarioRecompensa;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public List<Tarea> getEtiquetadas() {
		return etiquetadas;
	}

	public void setEtiquetadas(List<Tarea> etiquetadas) {
		this.etiquetadas = etiquetadas;
	}

	public boolean isAviable() {
		return isAvailable;
	}

	public void setAviable(boolean isAviable) {
		this.isAvailable = isAviable;
	}
	
	
	

}