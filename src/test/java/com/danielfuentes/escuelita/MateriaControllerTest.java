package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.MateriaController;
import com.danielfuentes.escuelita.model.Materia;
import com.danielfuentes.escuelita.service.MateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MateriaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(materiaController).build();
    }

    @Test
    void getAllMaterias() throws Exception {
        Materia materia = new Materia();
        materia.setNombre("Álgebra");
        materia.setProfesor("Luis Molina");
        materia.setCargaHoraria(60);

        when(materiaService.findAllMaterias()).thenReturn(Arrays.asList(materia));

        mockMvc.perform(get("/api/materias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Álgebra"))
                .andExpect(jsonPath("$[0].profesor").value("Luis Molina"))
                .andExpect(jsonPath("$[0].cargaHoraria").value(60));

        verify(materiaService, times(1)).findAllMaterias();
    }

    @Test
    void getMateriaById() throws Exception {
        Materia materia = new Materia();
        materia.setId(1L);
        materia.setNombre("Álgebra");
        materia.setProfesor("Luis Molina");
        materia.setCargaHoraria(60);

        when(materiaService.findMateriaById(1L)).thenReturn(Optional.of(materia));

        mockMvc.perform(get("/api/materias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Álgebra"))
                .andExpect(jsonPath("$.profesor").value("Luis Molina"))
                .andExpect(jsonPath("$.cargaHoraria").value(60));

        verify(materiaService, times(1)).findMateriaById(1L);
    }

    @Test
    void createMateria() throws Exception {
        Materia materia = new Materia();
        materia.setNombre("Álgebra");
        materia.setProfesor("Luis Molina");
        materia.setCargaHoraria(60);

        when(materiaService.saveMateria(any(Materia.class))).thenReturn(materia);

        mockMvc.perform(post("/api/materias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Álgebra\", \"profesor\": \"Luis Molina\", \"cargaHoraria\": 60}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Álgebra"))
                .andExpect(jsonPath("$.profesor").value("Luis Molina"))
                .andExpect(jsonPath("$.cargaHoraria").value(60));

        verify(materiaService, times(1)).saveMateria(any(Materia.class));
    }

    @Test
    void updateMateria() throws Exception {
        Materia materia = new Materia();
        materia.setId(1L);
        materia.setNombre("Álgebra");
        materia.setProfesor("Luis Molina");
        materia.setCargaHoraria(60);

        when(materiaService.updateMateria(eq(1L), any(Materia.class))).thenReturn(materia);

        mockMvc.perform(put("/api/materias/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Álgebra\", \"profesor\": \"Luis Molina\", \"cargaHoraria\": 60}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Álgebra"))
                .andExpect(jsonPath("$.profesor").value("Luis Molina"))
                .andExpect(jsonPath("$.cargaHoraria").value(60));

        verify(materiaService, times(1)).updateMateria(eq(1L), any(Materia.class));
    }

    @Test
    void deleteMateria() throws Exception {
        doNothing().when(materiaService).deleteMateria(1L);

        mockMvc.perform(delete("/api/materias/1"))
                .andExpect(status().isNoContent());

        verify(materiaService, times(1)).deleteMateria(1L);
    }
}
