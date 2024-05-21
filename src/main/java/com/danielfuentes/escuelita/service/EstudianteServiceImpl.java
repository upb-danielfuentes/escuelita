package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;


@Service
@Validated
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Estudiante> findAllActiveEstudiantes() {
        return estudianteRepository.findByActivo(true);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estudiante> findEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    @Override
    @Transactional
    public Estudiante saveEstudiante(@Valid Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    @Transactional
    public Estudiante updateEstudiante(Long id, @Valid Estudiante estudiante) {
        Optional<Estudiante> existingEstudiante = estudianteRepository.findById(id);
        if (existingEstudiante.isPresent()) {
            Estudiante est = existingEstudiante.get();
            est.setNombre(estudiante.getNombre());
            est.setApellido(estudiante.getApellido());
            est.setCodigo(estudiante.getCodigo());
            est.setActivo(estudiante.isActivo());
            return estudianteRepository.save(est);
        } else {
            throw new IllegalArgumentException("Estudiante no encontrado");
        }
    }

    @Override
    @Transactional
    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}
