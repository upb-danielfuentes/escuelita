package com.danielfuentes.escuelita.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "Estudiantes_Cursos")
@Getter
@Setter
public class EstudianteCurso {
    @Id
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @Id
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
