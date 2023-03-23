package com.gamificacion.demo.Models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the clases database table.
 * 
 */
@Entity
@NamedQuery(name="Clases.findAll", query="SELECT c FROM Clases c")
public class Clases implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;

	//bi-directional many-to-many association to Status
	@ManyToMany(mappedBy="clases")
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private List<Status> statuses;

	public Clases() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

}