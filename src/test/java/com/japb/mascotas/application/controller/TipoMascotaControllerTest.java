package com.japb.mascotas.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.japb.mascotas.application.dto.TipoMascotaDTO;
import com.japb.mascotas.application.mapper.TipoMascotaMapper;
import com.japb.mascotas.domain.model.TipoMascota;
import com.japb.mascotas.domain.service.TipoMascotaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TipoMascotaController.class)
@ExtendWith(MockitoExtension.class)
public class TipoMascotaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TipoMascotaService tipoMascotaService;

    @Mock
    private TipoMascotaMapper tipoMascotaMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTiposMascota_shouldReturnListOfTipoMascotaDTOs() throws Exception {
        // Arrange
        Long id = 1L;
        TipoMascota tipoMascota = new TipoMascota();
        tipoMascota.setNombre("test");
        TipoMascotaDTO tipoMascotaDTO = new TipoMascotaDTO();
        tipoMascotaDTO.setNombre("test");

        when(tipoMascotaService.getTipoMascotaById(id)).thenReturn(Optional.of(tipoMascota));
        when(tipoMascotaMapper.toDTO(tipoMascota)).thenReturn(tipoMascotaDTO);

        mockMvc.perform(get("/api/tipos-mascota/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("test"));
    }
/*
    @Test
    void getTipoMascotaById_existingId_shouldReturnTipoMascotaDTO() {
        // Arrange
        Long id = 1L;
        TipoMascota tipoMascota = new TipoMascota();
        TipoMascotaDTO tipoMascotaDTO = new TipoMascotaDTO();

        when(tipoMascotaService.getTipoMascotaById(id)).thenReturn(Optional.of(tipoMascota));
        when(tipoMascotaMapper.toDTO(tipoMascota)).thenReturn(tipoMascotaDTO);

        // Act
        ResponseEntity<TipoMascotaDTO> response = tipoMascotaController.getTipoMascotaById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tipoMascotaDTO, response.getBody());
        verify(tipoMascotaService).getTipoMascotaById(id);
        verify(tipoMascotaMapper).toDTO(tipoMascota);
    }

    @Test
    void getTipoMascotaById_nonExistingId_shouldReturnNotFound() {
        // Arrange
        Long id = 1L;
        when(tipoMascotaService.getTipoMascotaById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<TipoMascotaDTO> response = tipoMascotaController.getTipoMascotaById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(tipoMascotaService).getTipoMascotaById(id);
    }

    @Test
    void createTipoMascota_validInput_shouldReturnCreatedTipoMascotaDTO() {
        // Arrange
        TipoMascotaDTO inputDTO = new TipoMascotaDTO();
        TipoMascota tipoMascota = new TipoMascota();
        TipoMascota savedTipoMascota = new TipoMascota();
        TipoMascotaDTO outputDTO = new TipoMascotaDTO();

        when(tipoMascotaMapper.toEntity(inputDTO)).thenReturn(tipoMascota);
        when(tipoMascotaService.saveTipoMascota(tipoMascota)).thenReturn(savedTipoMascota);
        when(tipoMascotaMapper.toDTO(savedTipoMascota)).thenReturn(outputDTO);

        // Act
        TipoMascotaDTO response = tipoMascotaController.createTipoMascota(inputDTO);

        // Assert
        assertEquals(outputDTO, response);
        verify(tipoMascotaMapper).toEntity(inputDTO);
        verify(tipoMascotaService).saveTipoMascota(tipoMascota);
        verify(tipoMascotaMapper).toDTO(savedTipoMascota);
    }

    @Test
    void updateTipoMascota_validInput_shouldReturnUpdatedTipoMascotaDTO() {
        // Arrange
        Long id = 1L;
        TipoMascotaDTO inputDTO = new TipoMascotaDTO();
        TipoMascota tipoMascota = new TipoMascota();
        TipoMascota updatedTipoMascota = new TipoMascota();
        TipoMascotaDTO outputDTO = new TipoMascotaDTO();

        when(tipoMascotaMapper.toEntity(inputDTO)).thenReturn(tipoMascota);
        when(tipoMascotaService.updateTipoMascota(id, tipoMascota)).thenReturn(updatedTipoMascota);
        when(tipoMascotaMapper.toDTO(updatedTipoMascota)).thenReturn(outputDTO);

        // Act
        TipoMascotaDTO response = tipoMascotaController.updateTipoMascota(id, inputDTO);

        // Assert
        assertEquals(outputDTO, response);
        verify(tipoMascotaMapper).toEntity(inputDTO);
        verify(tipoMascotaService).updateTipoMascota(id, tipoMascota);
        verify(tipoMascotaMapper).toDTO(updatedTipoMascota);
    }

    @Test
    void deleteTipoMascota_shouldCallDeleteMethodInService() {
        // Arrange
        Long id = 1L;
        doNothing().when(tipoMascotaService).deleteTipoMascota(id);

        // Act
        tipoMascotaController.deleteTipoMascota(id);

        // Assert
        verify(tipoMascotaService).deleteTipoMascota(id);
    }

 */
}