package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.EstudianteController;
import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.service.EstudianteService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteControllerTest {

    @InjectMocks
    private EstudianteController estudianteController;

    @Mock
    private EstudianteService estudianteService;

    @Test
    public void whenGetActiveEstudiantes_thenReturnActiveEstudiantesList() {
        Estudiante estudiante1 = new Estudiante();
        Estudiante estudiante2 = new Estudiante();
        List<Estudiante> expectedEstudiantes = Arrays.asList(estudiante1, estudiante2);
        when(estudianteService.findAllActiveEstudiantes()).thenReturn(expectedEstudiantes);

        ResponseEntity<List<Estudiante>> response = estudianteController.getActiveEstudiantes();

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(estudianteService).findAllActiveEstudiantes();
    }

    @Test
    public void whenGetEstudianteById_thenReturnEstudiante() {
        Estudiante expectedEstudiante = new Estudiante();
        expectedEstudiante.setId(1L);
        when(estudianteService.findEstudianteById(1L)).thenReturn(Optional.of(expectedEstudiante));

        ResponseEntity<Estudiante> response = estudianteController.getEstudianteById(1L);

        assertTrue(response.getBody().getId().equals(1L));
        verify(estudianteService).findEstudianteById(1L);
    }

    @Test
    public void whenGetEstudianteById_notFound_thenReturnNotFoundResponse() {
        when(estudianteService.findEstudianteById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Estudiante> response = estudianteController.getEstudianteById(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(estudianteService).findEstudianteById(1L);
    }
}
