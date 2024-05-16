package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.DireccionController;
import com.danielfuentes.escuelita.model.Direccion;
import com.danielfuentes.escuelita.service.DireccionService;
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

public class DireccionControllerTest {

    @InjectMocks
    DireccionController direccionController;

    @Mock
    DireccionService direccionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDireccion() {
        Direccion direccion = new Direccion();
        when(direccionService.saveDireccion(any(Direccion.class))).thenReturn(direccion);

        Direccion response = direccionController.createDireccion(new Direccion());

        assertEquals(direccion, response);
        verify(direccionService, times(1)).saveDireccion(any(Direccion.class));
    }

    @Test
    public void testGetDireccionById() {
        Direccion direccion = new Direccion();
        direccion.setId(1L);
        when(direccionService.getDireccionById(1L)).thenReturn(Optional.of(direccion));

        ResponseEntity<Direccion> response = direccionController.getDireccionById(1L);

        assertEquals(1L, response.getBody().getId());
        verify(direccionService, times(1)).getDireccionById(1L);
    }

    @Test
    public void testGetAllDirecciones() {
        Direccion direccion1 = new Direccion();
        Direccion direccion2 = new Direccion();
        List<Direccion> direcciones = Arrays.asList(direccion1, direccion2);
        when(direccionService.getAllDirecciones()).thenReturn(direcciones);

        List<Direccion> response = direccionController.getAllDirecciones();

        assertEquals(2, response.size());
        verify(direccionService, times(1)).getAllDirecciones();
    }

    @Test
    public void testUpdateDireccion() {
        Direccion direccion = new Direccion();
        direccion.setId(1L);
        when(direccionService.getDireccionById(1L)).thenReturn(Optional.of(direccion));

        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.setDireccion("Nueva direccion");
        ResponseEntity<Direccion> response = direccionController.updateDireccion(1L, nuevaDireccion);

        assertEquals("Nueva direccion", response.getBody().getDireccion());
        verify(direccionService, times(1)).getDireccionById(1L);
        verify(direccionService, times(1)).saveDireccion(any(Direccion.class));
    }

    @Test
    public void testDeleteDireccion() {
        Direccion direccion = new Direccion();
        direccion.setId(1L);
        when(direccionService.getDireccionById(1L)).thenReturn(Optional.of(direccion));

        ResponseEntity<Object> response = direccionController.deleteDireccion(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(direccionService, times(1)).getDireccionById(1L);
        verify(direccionService, times(1)).deleteDireccion(1L);
    }
}