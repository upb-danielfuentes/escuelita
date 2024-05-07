package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> findAllActiveEstudiantes() {
        return estudianteRepository.findByActivo(true);
    }

    public Optional<Estudiante> findEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }
}
