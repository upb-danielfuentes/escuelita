package com.danielfuentes.escuelita.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "Cursos_Materias")
@Getter
@Setter
public class CursoMateria {
    @Id
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Id
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;
}
