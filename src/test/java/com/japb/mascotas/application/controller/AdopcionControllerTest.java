package com.japb.mascotas.application.controller;

import com.japb.mascotas.application.dto.AdopcionDTO;
import com.japb.mascotas.application.mapper.AdopcionMapper;
import com.japb.mascotas.domain.model.Adopcion;
import com.japb.mascotas.domain.service.AdopcionService;
import com.japb.mascotas.utils.AdopcionNotFoundException;
import com.japb.mascotas.utils.MascotaNotFoundException;
import com.japb.mascotas.utils.UsuarioNotFoundException;
import org.springframework.http.ResponseEntity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdopcionControllerTest {

    @InjectMocks
    private AdopcionController adopcionController;

    @Mock
    private AdopcionService adopcionService;

    @Mock
    private AdopcionMapper adopcionMapper;

    @Test
    void getAllAdopciones_shouldReturnListOfAdopcionDTOs() {
        // Arrange
        Adopcion adopcion = new Adopcion();
        AdopcionDTO adopcionDTO = new AdopcionDTO();
        when(adopcionService.getAllAdopciones()).thenReturn(List.of(adopcion));
        when(adopcionMapper.toDTO(adopcion)).thenReturn(adopcionDTO);

        // Act
        List<AdopcionDTO> result = adopcionController.getAllAdopciones();

        // Assert
        assertEquals(1, result.size());
        assertEquals(adopcionDTO, result.get(0));
        verify(adopcionService).getAllAdopciones();
        verify(adopcionMapper).toDTO(adopcion);
    }

    @Test
    void getAdopcionById_existingId_shouldReturnAdopcionDTO() {
        // Arrange
        Long id = 1L;
        Adopcion adopcion = new Adopcion();
        AdopcionDTO adopcionDTO = new AdopcionDTO();
        when(adopcionService.getAdopcionById(id)).thenReturn(Optional.of(adopcion));
        when(adopcionMapper.toDTO(adopcion)).thenReturn(adopcionDTO);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.getAdopcionById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(adopcionDTO, response.getBody());
        verify(adopcionService).getAdopcionById(id);
        verify(adopcionMapper).toDTO(adopcion);
    }

    @Test
    void getAdopcionById_nonExistingId_shouldReturnNotFound() {
        // Arrange
        Long id = 1L;
        when(adopcionService.getAdopcionById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.getAdopcionById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(adopcionService).getAdopcionById(id);
    }

    @Test
    void createAdopcion_validInput_shouldReturnCreatedAdopcionDTO() {
        // Arrange
        AdopcionDTO inputDTO = new AdopcionDTO();
        Adopcion adopcion = new Adopcion();
        Adopcion savedAdopcion = new Adopcion();
        AdopcionDTO outputDTO = new AdopcionDTO();

        when(adopcionMapper.toEntity(inputDTO)).thenReturn(adopcion);
        when(adopcionService.saveAdopcion(adopcion)).thenReturn(savedAdopcion);
        when(adopcionMapper.toDTO(savedAdopcion)).thenReturn(outputDTO);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.createAdopcion(inputDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(outputDTO, response.getBody());
        verify(adopcionMapper).toEntity(inputDTO);
        verify(adopcionService).saveAdopcion(adopcion);
        verify(adopcionMapper).toDTO(savedAdopcion);
    }

    @Test
    void createAdopcion_mascotaNotFound_shouldReturnBadRequest() {
        // Arrange
        AdopcionDTO inputDTO = new AdopcionDTO();
        when(adopcionMapper.toEntity(inputDTO)).thenThrow(MascotaNotFoundException.class);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.createAdopcion(inputDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(adopcionMapper).toEntity(inputDTO);
    }

    @Test
    void createAdopcion_usuarioNotFound_shouldReturnBadRequest() {
        // Arrange
        AdopcionDTO inputDTO = new AdopcionDTO();
        when(adopcionMapper.toEntity(inputDTO)).thenThrow(UsuarioNotFoundException.class);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.createAdopcion(inputDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(adopcionMapper).toEntity(inputDTO);
    }

    @Test
    void updateAdopcion_validInput_shouldReturnOk() {
        // Arrange
        Long id = 1L;
        AdopcionDTO inputDTO = new AdopcionDTO();
        Adopcion adopcion = new Adopcion();
        Adopcion updatedAdopcion = new Adopcion();
        AdopcionDTO outputDTO = new AdopcionDTO();

        when(adopcionMapper.toEntity(inputDTO)).thenReturn(adopcion);
        when(adopcionService.updateAdopcion(id, adopcion)).thenReturn(updatedAdopcion);
        when(adopcionMapper.toDTO(updatedAdopcion)).thenReturn(outputDTO);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.updateAdopcion(id, inputDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(outputDTO, response.getBody());
        verify(adopcionMapper).toEntity(inputDTO);
        verify(adopcionService).updateAdopcion(id, adopcion);
        verify(adopcionMapper).toDTO(updatedAdopcion);
    }

    @Test
    void updateAdopcion_mascotaNotFound_shouldReturnBadRequest() {
        // Arrange
        Long id = 1L;
        AdopcionDTO inputDTO = new AdopcionDTO();
        when(adopcionMapper.toEntity(inputDTO)).thenThrow(MascotaNotFoundException.class);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.updateAdopcion(id, inputDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(adopcionMapper).toEntity(inputDTO);
    }

    @Test
    void updateAdopcion_usuarioNotFound_shouldReturnBadRequest() {
        // Arrange
        Long id = 1L;
        AdopcionDTO inputDTO = new AdopcionDTO();
        when(adopcionMapper.toEntity(inputDTO)).thenThrow(UsuarioNotFoundException.class);

        // Act
        ResponseEntity<AdopcionDTO> response = adopcionController.updateAdopcion(id, inputDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(adopcionMapper).toEntity(inputDTO);
    }
    @Test
    void deleteAdopcion_shouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(adopcionService).deleteAdopcion(id); // Configura el mock para que no haga nada

        // Act
        ResponseEntity<Void> response = adopcionController.deleteAdopcion(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(adopcionService).deleteAdopcion(id); // Verifica que se llamó al servicio
    }

    @Test
    void deleteAdopcion_AdopcionNotFound_shouldReturnNotFound() {
        // Arrange
        Long id = 1L;
        doThrow(new AdopcionNotFoundException(id)).when(adopcionService).deleteAdopcion(id); // Configura el mock para lanzar la excepción

        // Act
        ResponseEntity<Void> response = adopcionController.deleteAdopcion(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(adopcionService).deleteAdopcion(id);
    }
}