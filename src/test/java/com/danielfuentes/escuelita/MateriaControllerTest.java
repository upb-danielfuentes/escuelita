package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.MateriaController;
import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.service.MateriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaControllerTest {

    @InjectMocks
    private MateriaController materiaController;

    @Mock
    private MateriaService materiaService;

    @Test
    public void whenGetMateriasByCursoId_thenReturnMateriasList() {
        Materia materia1 = new Materia();
        materia1.setNombre("Mathematics");
        Materia materia2 = new Materia();
        materia2.setNombre("Science");
        List<Materia> expectedMaterias = Arrays.asList(materia1, materia2);
        when(materiaService.findMateriasByCursoId(1L)).thenReturn(expectedMaterias);

        ResponseEntity<List<Materia>> response = materiaController.getMateriasByCursoId(1L);

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Mathematics", response.getBody().get(0).getNombre());
        verify(materiaService).findMateriasByCursoId(1L);
    }

    @Test
    public void whenNoMateriasFound_thenReturnEmptyList() {
        when(materiaService.findMateriasByCursoId(1L)).thenReturn(Arrays.asList());

        ResponseEntity<List<Materia>> response = materiaController.getMateriasByCursoId(1L);

        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(materiaService).findMateriasByCursoId(1L);
    }
}
