package com.danielfuentes.escuelita.service;


import com.danielfuentes.escuelita.model.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionService {
    Direccion saveDireccion(Direccion direccion);
    Optional<Direccion> getDireccionById(Long id);
    List<Direccion> getAllDirecciones();
    Direccion updateDireccion(Direccion direccion);
    void deleteDireccion(Long id);
}