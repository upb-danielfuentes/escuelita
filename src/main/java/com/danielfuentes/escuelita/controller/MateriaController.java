package com.danielfuentes.escuelita.controller;

import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<Materia>> getAllMaterias() {
        List<Materia> materias = materiaService.findAllMaterias();
        return ResponseEntity.ok(materias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Long id) {
        return materiaService.findMateriaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Materia> createMateria(@Valid @RequestBody Materia materia) {
        Materia createdMateria = materiaService.saveMateria(materia);
        return ResponseEntity.ok(createdMateria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable Long id, @Valid @RequestBody Materia materia) {
        try {
            Materia updatedMateria = materiaService.updateMateria(id, materia);
            return ResponseEntity.ok(updatedMateria);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        materiaService.deleteMateria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/profesores")
    public ResponseEntity<Set<String>> getProfesores() {
        Set<String> profesores = materiaService.findUniqueProfesores();
        return ResponseEntity.ok(profesores);
    }
}
