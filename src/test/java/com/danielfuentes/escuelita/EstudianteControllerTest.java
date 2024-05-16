package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.EstudianteController;
import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.service.EstudianteService;
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

public class EstudianteControllerTest {

    @InjectMocks
    EstudianteController estudianteController;

    @Mock
    EstudianteService estudianteService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetActiveEstudiantes() {
        Estudiante estudiante1 = new Estudiante();
        Estudiante estudiante2 = new Estudiante();
        List<Estudiante> estudiantes = Arrays.asList(estudiante1, estudiante2);
        when(estudianteService.findAllActiveEstudiantes()).thenReturn(estudiantes);

        ResponseEntity<List<Estudiante>> response = estudianteController.getActiveEstudiantes();

        assertEquals(2, response.getBody().size());
        verify(estudianteService, times(1)).findAllActiveEstudiantes();
    }

    @Test
    public void testGetEstudianteById() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        when(estudianteService.findEstudianteById(1L)).thenReturn(Optional.of(estudiante));

        ResponseEntity<Estudiante> response = estudianteController.getEstudianteById(1L);

        assertEquals(1L, response.getBody().getId());
        verify(estudianteService, times(1)).findEstudianteById(1L);
    }
}