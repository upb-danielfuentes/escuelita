package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.UsuarioController;
import com.danielfuentes.escuelita.model.Usuario;
import com.danielfuentes.escuelita.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Test
    public void whenCreateUsuario_thenUsuarioIsSavedAndReturned() {
        Usuario expectedUsuario = new Usuario();
        expectedUsuario.setNombre("Juan");
        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(expectedUsuario);

        Usuario result = usuarioController.createUsuario(new Usuario());

        assertEquals("Juan", result.getNombre());
        verify(usuarioService).saveUsuario(any(Usuario.class));
    }

    @Test
    public void whenGetUsuarioById_thenUsuarioIsReturned() {
        Usuario expectedUsuario = new Usuario();
        expectedUsuario.setId(1L);
        expectedUsuario.setNombre("Juan");
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(expectedUsuario));

        ResponseEntity<Usuario> response = usuarioController.getUsuarioById(1L);

        assertTrue(response.getBody().getId().equals(1L));
        assertEquals("Juan", response.getBody().getNombre());
        verify(usuarioService).getUsuarioById(1L);
    }

    @Test
    public void whenGetAllUsuarios_thenAllUsuariosAreReturned() {
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Juan");
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Maria");
        List<Usuario> expectedUsuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioService.getAllUsuarios()).thenReturn(expectedUsuarios);

        List<Usuario> response = usuarioController.getAllUsuarios();

        assertEquals(2, response.size());
        assertEquals("Juan", response.get(0).getNombre());
        assertEquals("Maria", response.get(1).getNombre());
        verify(usuarioService).getAllUsuarios();
    }

    @Test
    public void whenUpdateUsuario_thenUsuarioIsUpdated() {
        Usuario existingUsuario = new Usuario();
        existingUsuario.setId(1L);
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(existingUsuario));

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre("Updated name");
        when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(nuevoUsuario);

        Usuario response = usuarioController.updateUsuario(1L, nuevoUsuario);

        assertEquals("Updated name", response.getNombre());
        verify(usuarioService).getUsuarioById(1L);
        verify(usuarioService).updateUsuario(nuevoUsuario);
    }

    @Test
    public void whenDeleteUsuario_thenStatusCodeIs200() {
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(new Usuario()));

        ResponseEntity<Void> response = usuarioController.deleteUsuario(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(usuarioService).getUsuarioById(1L);
        verify(usuarioService).deleteUsuario(1L);
    }
}
