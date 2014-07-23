package com.compumovil.gameschedule;

import java.util.Date;

public class Evento {
	
	private String nombre;
	private String ciudad;
	private Date fechaInicio;
	private Date fechaFinal;
	private String contacto;
	private String latitud;
	private String longitud;
	private String descripcion;
	private int idUsuario;
	
	
	public Evento(String nombre, String ciudad, Date fechaInicio,
			Date fechaFinal, String contacto, String latitud, String longitud,
			String descripcion, int idUsuario) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.contacto = contacto;
		this.latitud = latitud;
		this.longitud = longitud;
		this.descripcion = descripcion;
		this.idUsuario = idUsuario;
	}
	
	public Evento() {
		
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public Date getFechaFinal() {
		return fechaFinal;
	}
	
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	public String getContacto() {
		return contacto;
	}
	
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	public String getLatitud() {
		return latitud;
	}
	
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	
	public String getLongitud() {
		return longitud;
	}
	
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
