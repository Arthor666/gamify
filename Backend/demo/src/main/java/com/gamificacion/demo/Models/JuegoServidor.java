package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the juego_servidor database table.
 * 
 */
@Entity
@Table(name="juego_servidor")
@NamedQuery(name="JuegoServidor.findAll", query="SELECT j FROM JuegoServidor j")
public class JuegoServidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="level_hdd")
	private int levelHdd;

	@Column(name="level_ram")
	private int levelRam;

	@Column(name="level_red")
	private int levelRed;

	@Column(name="level_server")
	private int levelServer;

	private double monedas;

	//bi-directional many-to-one association to Equipo
	@ManyToOne
	@JoinColumn(name="id_equipo")
	private Equipo equipo;

	public JuegoServidor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevelHdd() {
		return this.levelHdd;
	}

	public void setLevelHdd(int levelHdd) {
		this.levelHdd = levelHdd;
	}

	public int getLevelRam() {
		return this.levelRam;
	}

	public void setLevelRam(int levelRam) {
		this.levelRam = levelRam;
	}

	public int getLevelRed() {
		return this.levelRed;
	}

	public void setLevelRed(int levelRed) {
		this.levelRed = levelRed;
	}

	public int getLevelServer() {
		return this.levelServer;
	}

	public void setLevelServer(int levelServer) {
		this.levelServer = levelServer;
	}

	public double getMonedas() {
		return this.monedas;
	}

	public void setMonedas(double monedas) {
		this.monedas = monedas;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}