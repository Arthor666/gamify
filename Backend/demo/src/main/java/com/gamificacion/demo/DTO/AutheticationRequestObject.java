package com.gamificacion.demo.DTO;

import java.io.Serializable;

public class AutheticationRequestObject implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	private String correo;
	private String password;
	
	public AutheticationRequestObject() {
		
	}
	
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
