package com.danielfuentes.escuelita.service;

import com.danielfuentes.escuelita.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario saveUsuario(Usuario usuario);
    Optional<Usuario> getUsuarioById(Long id);
    List<Usuario> getAllUsuarios();
    Usuario updateUsuario(Usuario usuario);
    void deleteUsuario(Long id);
}
