package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.CursoController;
import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.service.CursoService;
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

public class CursoControllerTest {

    @InjectMocks
    CursoController cursoController;

    @Mock
    CursoService cursoService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCursosByEstudianteId() {
        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        List<Curso> cursos = Arrays.asList(curso1, curso2);
        when(cursoService.findCursosByEstudianteId(1L)).thenReturn(cursos);

        ResponseEntity<List<Curso>> response = cursoController.getCursosByEstudianteId(1L);

        assertEquals(2, response.getBody().size());
        verify(cursoService, times(1)).findCursosByEstudianteId(1L);
    }

    @Test
    public void testGetCursoById() {
        Curso curso = new Curso();
        curso.setId(1L);
        when(cursoService.findCursoById(1L)).thenReturn(Optional.of(curso));

        ResponseEntity<Curso> response = cursoController.getCursoById(1L);

        assertEquals(1L, response.getBody().getId());
        verify(cursoService, times(1)).findCursoById(1L);
    }
}