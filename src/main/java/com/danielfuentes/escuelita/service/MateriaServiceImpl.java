package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Validated
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Materia> findAllMaterias() {
        return materiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Materia> findMateriaById(Long id) {
        return materiaRepository.findById(id);
    }

    @Override
    @Transactional
    public Materia saveMateria(@Valid Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    @Transactional
    public Materia updateMateria(Long id, @Valid Materia materia) {
        Optional<Materia> existingMateria = materiaRepository.findById(id);
        if (existingMateria.isPresent()) {
            Materia m = existingMateria.get();
            m.setNombre(materia.getNombre());
            m.setProfesor(materia.getProfesor());
            m.setCargaHoraria(materia.getCargaHoraria());
            return materiaRepository.save(m);
        } else {
            throw new IllegalArgumentException("Materia no encontrada");
        }
    }

    @Override
    @Transactional
    public void deleteMateria(Long id) {
        materiaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> findUniqueProfesores() {
        List<Materia> materias = materiaRepository.findAll();
        Set<String> profesores = new HashSet<>();
        for (Materia materia : materias) {
            profesores.add(materia.getProfesor());
        }
        return profesores;
    }
}
