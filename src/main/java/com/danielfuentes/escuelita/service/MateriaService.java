package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MateriaService {
    List<Materia> findAllMaterias();
    Optional<Materia> findMateriaById(Long id);
    Materia saveMateria(Materia materia);
    Materia updateMateria(Long id, Materia materia);
    void deleteMateria(Long id);
    Set<String> findUniqueProfesores();
}
