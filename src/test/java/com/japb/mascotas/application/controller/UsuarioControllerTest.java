package com.japb.mascotas.application.controller;

import com.japb.mascotas.application.dto.UsuarioDTO;
import com.japb.mascotas.application.mapper.UsuarioMapper;
import com.japb.mascotas.domain.model.Usuario;
import com.japb.mascotas.domain.service.UsuarioService;
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

@ExtendWith(MockitoExtension.class) // *** ¡CRUCIAL! ***
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Test
    void getAllUsuarios_shouldReturnListOfUsuarioDTOs() {
        // Arrange
        Usuario usuario = new Usuario();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.getAllUsuarios()).thenReturn(List.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        // Act
        List<UsuarioDTO> response = usuarioController.getAllUsuarios();

        // Assert
        assertEquals(1, response.size());
        assertEquals(usuarioDTO, response.get(0));
        verify(usuarioService).getAllUsuarios();
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    void getUsuarioById_existingId_shouldReturnUsuarioDTO() {
        // Arrange
        Long id = 1L;
        Usuario usuario = new Usuario();
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        when(usuarioService.getUsuarioById(id)).thenReturn(Optional.of(usuario));
        when(usuarioMapper.toDTO(usuario)).thenReturn(usuarioDTO);

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.getUsuarioById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioDTO, response.getBody());
        verify(usuarioService).getUsuarioById(id);
        verify(usuarioMapper).toDTO(usuario);
    }

    @Test
    void getUsuarioById_nonExistingId_shouldReturnNotFound() {
        // Arrange
        Long id = 1L;
        when(usuarioService.getUsuarioById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.getUsuarioById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioService).getUsuarioById(id);
    }

    @Test
    void createUsuario_validInput_shouldReturnCreatedUsuarioDTO() {
        // Arrange
        UsuarioDTO inputDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();
        Usuario savedUsuario = new Usuario();
        UsuarioDTO outputDTO = new UsuarioDTO();

        when(usuarioMapper.toEntity(inputDTO)).thenReturn(usuario);
        when(usuarioService.saveUsuario(usuario)).thenReturn(savedUsuario);
        when(usuarioMapper.toDTO(savedUsuario)).thenReturn(outputDTO);

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.createUsuario(inputDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(outputDTO, response.getBody());
        verify(usuarioMapper).toEntity(inputDTO);
        verify(usuarioService).saveUsuario(usuario);
        verify(usuarioMapper).toDTO(savedUsuario);
    }

    @Test
    void updateUsuario_existingId_shouldReturnUpdatedUsuarioDTO() {
        // Arrange
        Long id = 1L;
        UsuarioDTO inputDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();
        Usuario updatedUsuario = new Usuario();
        UsuarioDTO outputDTO = new UsuarioDTO();

        when(usuarioMapper.toEntity(inputDTO)).thenReturn(usuario);
        when(usuarioService.updateUsuario(id, usuario)).thenReturn(updatedUsuario);
        when(usuarioMapper.toDTO(updatedUsuario)).thenReturn(outputDTO);

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.updateUsuario(id, inputDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(outputDTO, response.getBody());
        verify(usuarioMapper).toEntity(inputDTO);
        verify(usuarioService).updateUsuario(id, usuario);
        verify(usuarioMapper).toDTO(updatedUsuario);
    }

    @Test
    void updateUsuario_nonExistingId_shouldReturnNotFound() {
        // Arrange
        Long id = 1L;
        UsuarioDTO inputDTO = new UsuarioDTO();
        when(usuarioMapper.toEntity(inputDTO)).thenReturn(new Usuario());
        when(usuarioService.updateUsuario(eq(id), any(Usuario.class))).thenThrow(new RuntimeException());

        // Act
        ResponseEntity<UsuarioDTO> response = usuarioController.updateUsuario(id, inputDTO);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioService).updateUsuario(eq(id), any(Usuario.class)); // Corrected verification
    }

    @Test
    void deleteUsuario_shouldReturnNoContent() {
        // Arrange
        Long id = 1L;
        doNothing().when(usuarioService).deleteUsuario(id);

        // Act
        ResponseEntity<Void> response = usuarioController.deleteUsuario(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService).deleteUsuario(id);
    }
}