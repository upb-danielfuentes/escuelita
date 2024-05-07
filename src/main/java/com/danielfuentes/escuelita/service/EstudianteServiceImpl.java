package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EstudianteServiceImpl extends EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> findAllActiveEstudiantes() {
        return estudianteRepository.findByActivo(true);
    }

    @Override
    public Optional<Estudiante> findEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }
}
