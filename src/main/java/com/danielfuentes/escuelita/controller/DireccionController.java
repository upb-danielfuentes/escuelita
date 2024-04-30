package com.danielfuentes.escuelita.controller;

import com.danielfuentes.escuelita.model.Direccion;
import com.danielfuentes.escuelita.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @PostMapping
    public Direccion createDireccion(@RequestBody Direccion direccion) {
        return direccionService.saveDireccion(direccion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> getDireccionById(@PathVariable Long id) {
        return direccionService.getDireccionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Direccion> getAllDirecciones() {
        return direccionService.getAllDirecciones();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> updateDireccion(@PathVariable Long id, @RequestBody Direccion nuevaDireccion) {
        return direccionService.getDireccionById(id)
                .map(direccionExistente -> {
                    direccionExistente.setDireccion(nuevaDireccion.getDireccion());
                    direccionExistente.setUsuario(nuevaDireccion.getUsuario());  // Asegúrate de actualizar la relación correctamente
                    direccionService.saveDireccion(direccionExistente);
                    return ResponseEntity.ok(direccionExistente);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDireccion(@PathVariable Long id) {
        try {
            return direccionService.getDireccionById(id)
                    .map(direccion -> {
                        direccionService.deleteDireccion(id);
                        return ResponseEntity.ok().build();
                    })
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception details
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
