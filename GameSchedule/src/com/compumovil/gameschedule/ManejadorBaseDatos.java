package com.compumovil.gameschedule;

public class ManejadorBaseDatos {
	
	
	
	public static final String tUsuarios = "usuario";
	public static final String cUId = "_id";
	public static final String cUNombreUsuario = "nombreUsuario";
	public static final String cUConstrasena = "contrasena";
	public static final String cUNombre = "nombre";
	public static final String cUAlias = "alias";
	public static final String cUEquipo = "equipo";
	
	public static final String tEventos = "evento";
	public static final String cEId = "_id";
	public static final String cENombre = "nombre";
	public static final String cECiudad = "ciudad";
	public static final String cEFechaInicio = "fechaInicio";
	public static final String cEFechaFinalizacion = "fechaFinalizacion";
	public static final String cEContacto = "contacto";
	public static final String cELatitud = "latitud";
	public static final String cELongitud = "longitud";
	public static final String cEDescripcion = "descripcion";
	public static final String cEUsuario = "idUsuario";
	

	public static final String creacionTablaUsuario = "create table "+tUsuarios+" ("
			+cUId+ " integer primary key autoincrement,"
			+cUNombreUsuario+ " text not null,"
			+cUConstrasena+ " text not null,"
			+cUNombre+ " text not null,"
			+cUAlias+ " text,"
			+cUEquipo+ " text);";
	
	public static final String creacionTablaEvento = "create table "+tEventos+" ("
			+cEId+ " integer primary key autoincrement,"
			+cENombre+ " text not null,"
			+cECiudad+ " text not null,"
			+cEFechaInicio+ " text not null,"
			+cEFechaFinalizacion+ " text not null,"
			+cEContacto+ " text,"
			+cELatitud+ " text not null,"
			+cELongitud+ " text not null,"
			+cEDescripcion+ " text,"
			+cEUsuario+ " integer not null,"
			+"constraint foreign key("+cEUsuario+") references "+tUsuarios+"("+cUId+"));";
	}
