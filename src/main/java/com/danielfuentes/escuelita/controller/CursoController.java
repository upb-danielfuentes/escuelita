package com.danielfuentes.escuelita.controller;

import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    // Obtener todos los cursos de un estudiante específico
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<Curso>> getCursosByEstudianteId(@PathVariable Long estudianteId) {
        List<Curso> cursos = cursoService.findCursosByEstudianteId(estudianteId);
        return ResponseEntity.ok(cursos);
    }

    // Obtener detalles de un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Long id) {
        return cursoService.findCursoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
