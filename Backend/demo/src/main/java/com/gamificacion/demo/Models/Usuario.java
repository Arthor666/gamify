package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String correo;

	@Column(name="is_active")
	private boolean isActive;

	private String nombre;

	@Column(name="nombre_usuario")
	private String nombreUsuario;

	private String password;

	private double puntaje;

	//bi-directional many-to-one association to Equipo
	@OneToMany(mappedBy="jefe")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Equipo> equipos1;

	//bi-directional many-to-many association to Equipo
	@ManyToMany(mappedBy="usuarios")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Equipo> equipos2;

	//bi-directional many-to-one association to Solicitud
	@OneToMany(mappedBy="usuario")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Solicitud> solicituds;

	//bi-directional many-to-many association to Subequipo
	@ManyToMany(mappedBy="usuarios")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Subequipo> subequipos;

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

	public List<Equipo> getEquipos1() {
		return this.equipos1;
	}

	public void setEquipos1(List<Equipo> equipos1) {
		this.equipos1 = equipos1;
	}

	public Equipo addEquipos1(Equipo equipos1) {
		getEquipos1().add(equipos1);
		equipos1.getUsuarios().add(this);

		return equipos1;
	}

	public Equipo removeEquipos1(Equipo equipos1) {
		getEquipos1().remove(equipos1);
		equipos1.setUsuarios(null);

		return equipos1;
	}

	public List<Equipo> getEquipos2() {
		return this.equipos2;
	}

	public void setEquipos2(List<Equipo> equipos2) {
		this.equipos2 = equipos2;
	}

	public List<Solicitud> getSolicituds() {
		return this.solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public Solicitud addSolicitud(Solicitud solicitud) {
		getSolicituds().add(solicitud);
		solicitud.setUsuario(this);

		return solicitud;
	}

	public Solicitud removeSolicitud(Solicitud solicitud) {
		getSolicituds().remove(solicitud);
		solicitud.setUsuario(null);

		return solicitud;
	}

	public List<Subequipo> getSubequipos() {
		return this.subequipos;
	}

	public void setSubequipos(List<Subequipo> subequipos) {
		this.subequipos = subequipos;
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

	public UsuarioRecompensa removeUsuarioRecompensa(UsuarioRecompensa usuarioRecompensa) {
		getUsuarioRecompensas().remove(usuarioRecompensa);
		usuarioRecompensa.setUsuario(null);

		return usuarioRecompensa;
	}

}