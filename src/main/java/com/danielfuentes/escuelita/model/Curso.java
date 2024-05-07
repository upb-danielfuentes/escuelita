package com.danielfuentes.escuelita.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cursos")
@Getter
@Setter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Integer duracion;

    @Column(nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "curso")
    private Set<EstudianteCurso> estudiantes;

    @OneToMany(mappedBy = "curso")
    private Set<CursoMateria> materias;
}

