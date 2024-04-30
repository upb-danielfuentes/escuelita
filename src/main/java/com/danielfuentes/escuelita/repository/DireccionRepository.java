package com.danielfuentes.escuelita.repository;

import com.danielfuentes.escuelita.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
