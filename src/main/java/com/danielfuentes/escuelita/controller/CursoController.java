package com.danielfuentes.escuelita.controller;

import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Obtener todos los cursos
    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        List<Curso> cursos = cursoService.findAllCursos();
        return ResponseEntity.ok(cursos);
    }

    // Obtener detalles de un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoService.findCursoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<Curso> createCurso(@Valid @RequestBody Curso curso) {
        Curso createdCurso = cursoService.saveCurso(curso);
        return ResponseEntity.ok(createdCurso);
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @Valid @RequestBody Curso curso) {
        try {
            Curso updatedCurso = cursoService.updateCurso(id, curso);
            return ResponseEntity.ok(updatedCurso);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }
}