package com.danielfuentes.escuelita.repository;

import com.danielfuentes.escuelita.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
