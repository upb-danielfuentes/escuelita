package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EstudianteService {
    List<Estudiante> findAllActiveEstudiantes();
    Optional<Estudiante> findEstudianteById(Long id);
    Estudiante saveEstudiante(Estudiante estudiante);
    Estudiante updateEstudiante(Long id, Estudiante estudiante);
    void deleteEstudiante(Long id);
}
