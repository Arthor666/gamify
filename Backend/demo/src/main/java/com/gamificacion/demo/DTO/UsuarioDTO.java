package com.gamificacion.demo.DTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamificacion.demo.Models.Equipo;
import com.gamificacion.demo.Models.Grupo;
import com.gamificacion.demo.Models.Notificacion;
import com.gamificacion.demo.Models.Proyecto;
import com.gamificacion.demo.Models.Rol;
import com.gamificacion.demo.Models.Tarea;

public class UsuarioDTO implements Serializable {
	
	public UsuarioDTO() {
		
	}
	
	private static final long serialVersionUID = 1L;
	
	private int id;

	private String correo;
	
	private boolean isActive;


	private boolean isAvailable;

	private String nombre;


	private String nombreUsuario;

	private String password;
	
	private List<Equipo>equipos;
	
	private List<Tarea> tareas;
	
	private List<Tarea> etiquetadas;	

	private double puntaje;
	
	private List<Notificacion> notificaciones;

	private Rol rol;
	
	private List<Grupo> grupos;
	
	
	private List<Grupo> grupo_profesor;
	
	private List<Proyecto> proyectos;
	
	private String token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
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

	public double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Grupo> getGrupo_profesor() {
		return grupo_profesor;
	}

	public void setGrupo_profesor(List<Grupo> grupo_profesor) {
		this.grupo_profesor = grupo_profesor;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	


}
