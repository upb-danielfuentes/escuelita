package com.danielfuentes.escuelita.controller;

import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // Obtener todos los estudiantes activos
    @GetMapping("/activos")
    public ResponseEntity<List<Estudiante>> getActiveEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.findAllActiveEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

    // Obtener detalles de un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        return estudianteService.findEstudianteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
