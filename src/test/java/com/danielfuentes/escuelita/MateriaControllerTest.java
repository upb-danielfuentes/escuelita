package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.MateriaController;
import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.service.MateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MateriaControllerTest {

    @InjectMocks
    MateriaController materiaController;

    @Mock
    MateriaService materiaService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMateriasByCursoId() {
        Materia materia1 = new Materia();
        Materia materia2 = new Materia();
        List<Materia> materias = Arrays.asList(materia1, materia2);
        when(materiaService.findMateriasByCursoId(1L)).thenReturn(materias);

        ResponseEntity<List<Materia>> response = materiaController.getMateriasByCursoId(1L);

        assertEquals(2, response.getBody().size());
        verify(materiaService, times(1)).findMateriasByCursoId(1L);
    }
}