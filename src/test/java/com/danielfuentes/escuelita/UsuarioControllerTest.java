package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.UsuarioController;
import com.danielfuentes.escuelita.model.Usuario;
import com.danielfuentes.escuelita.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @InjectMocks
    UsuarioController usuarioController;

    @Mock
    UsuarioService usuarioService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(usuario);

        Usuario response = usuarioController.createUsuario(new Usuario());

        assertEquals(usuario, response);
        verify(usuarioService, times(1)).saveUsuario(any(Usuario.class));
    }

    @Test
    public void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1L);

        assertEquals(1L, response.getBody().getId());
        verify(usuarioService, times(1)).getUsuarioById(1L);
    }

    @Test
    public void testGetAllUsuarios() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        List<Usuario> response = usuarioController.getAllUsuarios();

        assertEquals(2, response.size());
        verify(usuarioService, times(1)).getAllUsuarios();
    }

    @Test
    public void testUpdateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(usuario));

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Nuevo nombre");
        Usuario response = usuarioController.updateUsuario(1L, nuevoUsuario);

        assertEquals("Nuevo nombre", response.getNombre());
        verify(usuarioService, times(1)).getUsuarioById(1L);
        verify(usuarioService, times(1)).updateUsuario(any(Usuario.class));
    }

    @Test
    public void testDeleteUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<Void> response = usuarioController.deleteUsuario(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(usuarioService, times(1)).getUsuarioById(1L);
        verify(usuarioService, times(1)).deleteUsuario(1L);
    }
}