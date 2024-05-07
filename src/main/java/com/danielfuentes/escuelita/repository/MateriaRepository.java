package com.danielfuentes.escuelita.repository;

import com.danielfuentes.escuelita.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    List<Materia> findByCursos_Curso_Id(Long cursoId);
}
