package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.DireccionController;
import com.danielfuentes.escuelita.model.Direccion;
import com.danielfuentes.escuelita.service.DireccionService;
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
public class DireccionControllerTest {

    @InjectMocks
    private DireccionController direccionController;

    @Mock
    private DireccionService direccionService;

    @Test
    public void whenCreateDireccion_thenSaveAndReturnSame() {
        Direccion expectedDireccion = new Direccion();
        when(direccionService.saveDireccion(any(Direccion.class))).thenReturn(expectedDireccion);

        Direccion response = direccionController.createDireccion(new Direccion());

        assertSame(expectedDireccion, response);
        verify(direccionService).saveDireccion(any(Direccion.class));
    }

    @Test
    public void whenGetDireccionById_thenReturnDireccion() {
        Direccion expectedDireccion = new Direccion();
        expectedDireccion.setId(1L);
        when(direccionService.getDireccionById(1L)).thenReturn(Optional.of(expectedDireccion));

        ResponseEntity<Direccion> response = direccionController.getDireccionById(1L);

        assertTrue(response.getBody().getId().equals(1L));
        verify(direccionService).getDireccionById(1L);
    }

    @Test
    public void whenGetAllDirecciones_thenReturnDireccionesList() {
        Direccion direccion1 = new Direccion();
        Direccion direccion2 = new Direccion();
        List<Direccion> expectedDirecciones = Arrays.asList(direccion1, direccion2);
        when(direccionService.getAllDirecciones()).thenReturn(expectedDirecciones);

        List<Direccion> response = direccionController.getAllDirecciones();

        assertEquals(expectedDirecciones.size(), response.size());
        verify(direccionService).getAllDirecciones();
    }

    @Test
    public void whenUpdateDireccion_thenUpdated() {
        Direccion existingDireccion = new Direccion();
        existingDireccion.setId(1L);
        when(direccionService.getDireccionById(1L)).thenReturn(Optional.of(existingDireccion));

        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.setDireccion("Nueva direccion");
        when(direccionService.saveDireccion(any(Direccion.class))).thenReturn(nuevaDireccion);

        ResponseEntity<Direccion> response = direccionController.updateDireccion(1L, nuevaDireccion);

        assertEquals("Nueva direccion", response.getBody().getDireccion());
        verify(direccionService).getDireccionById(1L);
        verify(direccionService).saveDireccion(nuevaDireccion);
    }

    @Test
    public void whenDeleteDireccion_thenVerifyDeleted() {
        when(direccionService.getDireccionById(1L)).thenReturn(Optional.of(new Direccion()));

        ResponseEntity<?> response = direccionController.deleteDireccion(1L);

        assertEquals(200, response.getStatusCodeValue());
        verify(direccionService).deleteDireccion(1L);
    }
}
