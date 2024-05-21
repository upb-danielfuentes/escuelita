package com.danielfuentes.escuelita;

import com.danielfuentes.escuelita.controller.EstudianteController;
import com.danielfuentes.escuelita.model.Estudiante;
import com.danielfuentes.escuelita.service.EstudianteService;
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

class EstudianteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EstudianteService estudianteService;

    @InjectMocks
    private EstudianteController estudianteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(estudianteController).build();
    }

    @Test
    void getActiveEstudiantes() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setApellido("Perez");
        estudiante.setCodigo("JP123");
        estudiante.setActivo(true);

        when(estudianteService.findAllActiveEstudiantes()).thenReturn(Arrays.asList(estudiante));

        mockMvc.perform(get("/api/estudiantes/activos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].codigo").value("JP123"))
                .andExpect(jsonPath("$[0].activo").value(true));

        verify(estudianteService, times(1)).findAllActiveEstudiantes();
    }

    @Test
    void getEstudianteById() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        estudiante.setApellido("Perez");
        estudiante.setCodigo("JP123");

        when(estudianteService.findEstudianteById(1L)).thenReturn(Optional.of(estudiante));

        mockMvc.perform(get("/api/estudiantes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.codigo").value("JP123"));

        verify(estudianteService, times(1)).findEstudianteById(1L);
    }

    @Test
    void createEstudiante() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Juan");
        estudiante.setApellido("Perez");
        estudiante.setCodigo("JP123");

        when(estudianteService.saveEstudiante(any(Estudiante.class))).thenReturn(estudiante);

        mockMvc.perform(post("/api/estudiantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan\", \"apellido\": \"Perez\", \"codigo\": \"JP123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.codigo").value("JP123"));

        verify(estudianteService, times(1)).saveEstudiante(any(Estudiante.class));
    }

    @Test
    void updateEstudiante() throws Exception {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1L);
        estudiante.setNombre("Juan");
        estudiante.setApellido("Perez");
        estudiante.setCodigo("JP123");

        when(estudianteService.updateEstudiante(eq(1L), any(Estudiante.class))).thenReturn(estudiante);

        mockMvc.perform(put("/api/estudiantes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan\", \"apellido\": \"Perez\", \"codigo\": \"JP123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.codigo").value("JP123"));

        verify(estudianteService, times(1)).updateEstudiante(eq(1L), any(Estudiante.class));
    }

    @Test
    void deleteEstudiante() throws Exception {
        doNothing().when(estudianteService).deleteEstudiante(1L);

        mockMvc.perform(delete("/api/estudiantes/1"))
                .andExpect(status().isNoContent());

        verify(estudianteService, times(1)).deleteEstudiante(1L);
    }
}

