create table usuario(
	_id integer primary key auto_increment,
	nombreUsuario varchar(250) not null,
	contrasena varchar(250) not null,
	nombre varchar(250) not null,
	alias varchar(250),
	equipo varchar(250)
);

create table evento(
	_id integer primary key auto_increment,
	nombre varchar(250) not null,
	ciudad varchar(250) not null,
	fechaInicio date not null,
	fechaFinalizacion date not null,
	contacto varchar(250),
	latitud varchar(250) not null,
	longitud varchar(250) not null,
	descripcion text,
	idUsuario integer not null,
	constraint foreign key (idUsuario) references usuario (_id)
);