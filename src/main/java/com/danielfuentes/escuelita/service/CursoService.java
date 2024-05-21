package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> findAllCursos();
    Optional<Curso> findCursoById(Long id);
    Curso saveCurso(Curso curso);
    Curso updateCurso(Long id, Curso curso);
    void deleteCurso(Long id);
}
