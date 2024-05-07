package com.danielfuentes.escuelita.controller;

import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias de un curso específico
    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<Materia>> getMateriasByCursoId(@PathVariable Long cursoId) {
        List<Materia> materias = materiaService.findMateriasByCursoId(cursoId);
        return ResponseEntity.ok(materias);
    }
}
