package com.japb.mascotas.application.controller;

import com.japb.mascotas.application.controller.MascotaController;
import com.japb.mascotas.application.dto.MascotaDTO;
import com.japb.mascotas.application.mapper.MascotaMapper;
import com.japb.mascotas.domain.model.Mascota;
import com.japb.mascotas.domain.service.MascotaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MascotaControllerTest {

    @InjectMocks
    private MascotaController mascotaController;

    @Mock
    private MascotaService mascotaService;

    @Mock
    private MascotaMapper mascotaMapper;

    @Test
    void getAllMascotas_shouldReturnListOfMascotaDTOs() {
        // Arrange
        Mascota mascota = new Mascota();
        MascotaDTO mascotaDTO = new MascotaDTO();
        when(mascotaService.getAllMascotas()).thenReturn(List.of(mascota));
        when(mascotaMapper.toDTO(mascota)).thenReturn(mascotaDTO);

        // Act
        List<MascotaDTO> result = mascotaController.getAllMascotas();

        // Assert
        assertEquals(1, result.size());
        assertEquals(mascotaDTO, result.get(0));
        verify(mascotaService).getAllMascotas();
        verify(mascotaMapper).toDTO(mascota);
    }

    @Test
    void getMascotaById_existingId_shouldReturnMascotaDTO() {
        // Arrange
        Long id = 1L;
        Mascota mascota = new Mascota();
        MascotaDTO mascotaDTO = new MascotaDTO();
        when(mascotaService.getMascotaById(id)).thenReturn(Optional.of(mascota));
        when(mascotaMapper.toDTO(mascota)).thenReturn(mascotaDTO);

        // Act
        ResponseEntity<MascotaDTO> response = mascotaController.getMascotaById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mascotaDTO, response.getBody());
        verify(mascotaService).getMascotaById(id);
        verify(mascotaMapper).toDTO(mascota);
    }

    @Test
    void getMascotaById_nonExistingId_shouldReturnNotFound() {
        // Arrange
        Long id = 1L;
        when(mascotaService.getMascotaById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<MascotaDTO> response = mascotaController.getMascotaById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(mascotaService).getMascotaById(id);
    }

    @Test
    void createMascota_validInput_shouldReturnCreatedMascotaDTO() {
        // Arrange
        MascotaDTO inputDTO = new MascotaDTO();
        Mascota mascota = new Mascota();
        Mascota savedMascota = new Mascota();
        MascotaDTO outputDTO = new MascotaDTO();

        when(mascotaMapper.toEntity(inputDTO)).thenReturn(mascota);
        when(mascotaService.saveMascota(mascota)).thenReturn(savedMascota);
        when(mascotaMapper.toDTO(savedMascota)).thenReturn(outputDTO);

        // Act
        MascotaDTO response = mascotaController.createMascota(inputDTO);

        // Assert
        assertEquals(outputDTO, response);
        verify(mascotaMapper).toEntity(inputDTO);
        verify(mascotaService).saveMascota(mascota);
        verify(mascotaMapper).toDTO(savedMascota);
    }

    @Test
    void updateMascota_validInput_shouldReturnUpdatedMascotaDTO() {
        // Arrange
        Long id = 1L;
        MascotaDTO inputDTO = new MascotaDTO();
        Mascota mascota = new Mascota();
        Mascota updatedMascota = new Mascota();
        MascotaDTO outputDTO = new MascotaDTO();

        when(mascotaMapper.toEntity(inputDTO)).thenReturn(mascota);
        when(mascotaService.updateMascota(id, mascota)).thenReturn(updatedMascota);
        when(mascotaMapper.toDTO(updatedMascota)).thenReturn(outputDTO);

        // Act
        MascotaDTO response = mascotaController.updateMascota(id, inputDTO);

        // Assert
        assertEquals(outputDTO, response);
        verify(mascotaMapper).toEntity(inputDTO);
        verify(mascotaService).updateMascota(id, mascota);
        verify(mascotaMapper).toDTO(updatedMascota);
    }

    @Test
    void deleteMascota_shouldCallDeleteMethodInService() {
        // Arrange
        Long id = 1L;
        doNothing().when(mascotaService).deleteMascota(id);

        // Act
        mascotaController.deleteMascota(id);

        // Assert
        verify(mascotaService).deleteMascota(id);
    }

}