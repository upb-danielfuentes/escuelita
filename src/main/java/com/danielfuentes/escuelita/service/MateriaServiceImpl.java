package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MateriaServiceImpl extends MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<Materia> findMateriasByCursoId(Long cursoId) {
        return materiaRepository.findByCursos_Curso_Id(cursoId);
    }
}

