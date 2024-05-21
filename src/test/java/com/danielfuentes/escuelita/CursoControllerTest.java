package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.CursoController;
import com.danielfuentes.escuelita.model.Curso;
import com.danielfuentes.escuelita.service.CursoService;
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

class CursoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();
    }

    @Test
    void getAllCursos() throws Exception {
        Curso curso = new Curso();
        curso.setNombre("Matemáticas");
        curso.setDescripcion("Curso de matemáticas básicas");
        curso.setDuracion(120);
        curso.setActivo(true);

        when(cursoService.findAllCursos()).thenReturn(Arrays.asList(curso));

        mockMvc.perform(get("/api/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Matemáticas"))
                .andExpect(jsonPath("$[0].descripcion").value("Curso de matemáticas básicas"))
                .andExpect(jsonPath("$[0].duracion").value(120))
                .andExpect(jsonPath("$[0].activo").value(true));

        verify(cursoService, times(1)).findAllCursos();
    }

    @Test
    void getCursoById() throws Exception {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNombre("Matemáticas");
        curso.setDescripcion("Curso de matemáticas básicas");
        curso.setDuracion(120);

        when(cursoService.findCursoById(1L)).thenReturn(Optional.of(curso));

        mockMvc.perform(get("/api/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Matemáticas"))
                .andExpect(jsonPath("$.descripcion").value("Curso de matemáticas básicas"))
                .andExpect(jsonPath("$.duracion").value(120));

        verify(cursoService, times(1)).findCursoById(1L);
    }

    @Test
    void createCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNombre("Matemáticas");
        curso.setDescripcion("Curso de matemáticas básicas");
        curso.setDuracion(120);

        when(cursoService.saveCurso(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Matemáticas\", \"descripcion\": \"Curso de matemáticas básicas\", \"duracion\": 120}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Matemáticas"))
                .andExpect(jsonPath("$.descripcion").value("Curso de matemáticas básicas"))
                .andExpect(jsonPath("$.duracion").value(120));

        verify(cursoService, times(1)).saveCurso(any(Curso.class));
    }

    @Test
    void updateCurso() throws Exception {
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setNombre("Matemáticas");
        curso.setDescripcion("Curso de matemáticas básicas");
        curso.setDuracion(120);

        when(cursoService.updateCurso(eq(1L), any(Curso.class))).thenReturn(curso);

        mockMvc.perform(put("/api/cursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Matemáticas\", \"descripcion\": \"Curso de matemáticas básicas\", \"duracion\": 120}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Matemáticas"))
                .andExpect(jsonPath("$.descripcion").value("Curso de matemáticas básicas"))
                .andExpect(jsonPath("$.duracion").value(120));

        verify(cursoService, times(1)).updateCurso(eq(1L), any(Curso.class));
    }

    @Test
    void deleteCurso() throws Exception {
        doNothing().when(cursoService).deleteCurso(1L);

        mockMvc.perform(delete("/api/cursos/1"))
                .andExpect(status().isNoContent());

        verify(cursoService, times(1)).deleteCurso(1L);
    }
}
