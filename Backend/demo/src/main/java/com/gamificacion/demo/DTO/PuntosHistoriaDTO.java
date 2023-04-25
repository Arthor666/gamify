package com.gamificacion.demo.DTO;

import java.io.Serializable;

public class PuntosHistoriaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private double totalPuntos;	
	
	public PuntosHistoriaDTO() {
		
	}

	public double getTotalPuntos() {
		return totalPuntos;
	}

	public void setTotalPuntos(double totalPuntos) {
		this.totalPuntos = totalPuntos;
	}
	
	

}
