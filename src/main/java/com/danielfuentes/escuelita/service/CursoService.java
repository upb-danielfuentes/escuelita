package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findCursosByEstudianteId(Long estudianteId) {
        return cursoRepository.findByEstudiantes_Estudiante_Id(estudianteId);
    }

    public Optional<Curso> findCursoById(Long id) {
        return cursoRepository.findById(id);
    }
}