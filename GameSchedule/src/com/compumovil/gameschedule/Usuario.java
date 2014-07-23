package com.compumovil.gameschedule;

public class Usuario {
	
	private String nombreUsario;
	private String constrasena;
	private String nombre;
	private String alias;
	private String equipo;
	
	
	
	public Usuario() {
		
	}

	public Usuario(String nombreUsario, String constrasena, String nombre,
			String alias, String equipo) {
		this.nombreUsario = nombreUsario;
		this.constrasena = constrasena;
		this.nombre = nombre;
		this.alias = alias;
		this.equipo = equipo;
	}

	public String getNombreUsario() {
		return nombreUsario;
	}
	
	public void setNombreUsario(String nombreUsario) {
		this.nombreUsario = nombreUsario;
	}
	
	public String getConstrasena() {
		return constrasena;
	}
	
	public void setConstrasena(String constrasena) {
		this.constrasena = constrasena;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getEquipo() {
		return equipo;
	}
	
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	
	

}
