-- we don't know how to generate root <with-no-name> (class Root) :(
create database cruddb;
create table direcciones
(
    id          bigint auto_increment
        primary key,
    direccion   varchar(255) not null,
    usuarios_id bigint       null,
    constraint direcciones_ibfk_1
        foreign key (usuarios_id) references cruddb.usuarios (id)
            on update cascade on delete cascade
);

create table usuarios
(
    id     bigint auto_increment
        primary key,
    nombre varchar(255) not null,
    email  varchar(255) not null,
    constraint email
        unique (email)
);

CREATE TABLE Estudiantes (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL,
                             apellido VARCHAR(100) NOT NULL,
                             codigo VARCHAR(50) NOT NULL,
                             activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Cursos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL,
                        descripcion TEXT,
                        duracion INT,
                        activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE Materias (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          profesor VARCHAR(100),
                          carga_horaria INT
);

CREATE TABLE Estudiantes_Cursos (
                                    estudiante_id INT,
                                    curso_id INT,
                                    FOREIGN KEY (estudiante_id) REFERENCES Estudiantes(id),
                                    FOREIGN KEY (curso_id) REFERENCES Cursos(id),
                                    PRIMARY KEY (estudiante_id, curso_id)
);

CREATE TABLE Cursos_Materias (
                                 curso_id INT,
                                 materia_id INT,
                                 FOREIGN KEY (curso_id) REFERENCES Cursos(id),
                                 FOREIGN KEY (materia_id) REFERENCES Materias(id),
                                 PRIMARY KEY (curso_id, materia_id)
);
