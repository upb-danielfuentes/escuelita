package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
@Validated
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAllCursos() {
        return cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findCursoById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso saveCurso(@Valid Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Curso updateCurso(Long id, @Valid Curso curso) {
        Optional<Curso> existingCurso = cursoRepository.findById(id);
        if (existingCurso.isPresent()) {
            Curso c = existingCurso.get();
            c.setNombre(curso.getNombre());
            c.setDescripcion(curso.getDescripcion());
            c.setDuracion(curso.getDuracion());
            c.setActivo(curso.isActivo());
            return cursoRepository.save(c);
        } else {
            throw new IllegalArgumentException("Curso no encontrado");
        }
    }

    @Override
    @Transactional
    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}