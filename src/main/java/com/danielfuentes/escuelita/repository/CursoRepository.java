package com.danielfuentes.escuelita.repository;

import com.danielfuentes.escuelita.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByEstudiantes_Estudiante_Id(Long estudianteId);
}

