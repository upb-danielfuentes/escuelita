package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Direccion;
import com.danielfuentes.escuelita.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public Direccion saveDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    public Optional<Direccion> getDireccionById(Long id) {
        return direccionRepository.findById(id);
    }

    @Override
    public List<Direccion> getAllDirecciones() {
        return direccionRepository.findAll();
    }

    @Override
    public Direccion updateDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    public void deleteDireccion(Long id) {
        direccionRepository.deleteById(id);
    }
}