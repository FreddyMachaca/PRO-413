create database crud_db;
use crud_db;

create table pelicula(
    id_pelicula int (11) not null auto_increment primary key,
    fecha_estreno date,
    ruta_portada varchar(255),
    sinopsis varchar(255),
    titulo varchar(255),
    youtube_trailer varchar(255)
);

create table genero_pelicula(
    id_pelicula int (11),
    id_genero int (11),
    foreign key (id_pelicula) references pelicula(id_pelicula),
    foreign key (id_genero) references genero(id_genero)
);

create table genero(
    id_genero int (11) not null auto_increment primary key,
    titulo varchar(255)
);

insert into genero (titulo) values ('Comedia');
insert into genero (titulo) values ('Terror');
insert into genero (titulo) values ('Acccion');
insert into genero (titulo) values ('Animacion');
