package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.CursoController;
import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.service.CursoService;
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
public class CursoControllerTest {

    @InjectMocks
    private CursoController cursoController;

    @Mock
    private CursoService cursoService;

    @Test
    public void whenGetCursosByEstudianteId_thenReturnCursoList() {
        Curso curso1 = new Curso(); curso1.setNombre("Math");
        Curso curso2 = new Curso(); curso2.setNombre("Science");
        List<Curso> expectedCursos = Arrays.asList(curso1, curso2);
        when(cursoService.findCursosByEstudianteId(1L)).thenReturn(expectedCursos);

        ResponseEntity<List<Curso>> response = cursoController.getCursosByEstudianteId(1L);

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Math", response.getBody().get(0).getNombre());
        verify(cursoService).findCursosByEstudianteId(1L);
    }

    @Test
    public void whenGetCursoById_thenReturnCurso() {
        Curso expectedCurso = new Curso();
        expectedCurso.setId(1L);
        expectedCurso.setNombre("Math");
        when(cursoService.findCursoById(1L)).thenReturn(Optional.of(expectedCurso));

        ResponseEntity<Curso> response = cursoController.getCursoById(1L);

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Math", response.getBody().getNombre());
        verify(cursoService).findCursoById(1L);
    }

    @Test
    public void whenGetCursoById_notFound_thenReturnNotFound() {
        when(cursoService.findCursoById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Curso> response = cursoController.getCursoById(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(cursoService).findCursoById(1L);
    }
}
