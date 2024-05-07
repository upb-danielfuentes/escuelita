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

