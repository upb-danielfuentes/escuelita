package com.danielfuentes.escuelita.repository;

import com.danielfuentes.escuelita.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    List<Estudiante> findByActivo(boolean activo);
}
