package com.japb.mascotas.application.controller;

import com.japb.mascotas.application.dto.AdopcionDTO;
import com.japb.mascotas.application.mapper.AdopcionMapper;
import com.japb.mascotas.domain.model.Adopcion;
import com.japb.mascotas.domain.service.AdopcionService;
import com.japb.mascotas.utils.MascotaNotFoundException;
import com.japb.mascotas.utils.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {


    private final AdopcionService adopcionService;

    private final AdopcionMapper adopcionMapper;

    public AdopcionController(AdopcionService adopcionService, AdopcionMapper adopcionMapper) {
        this.adopcionService = adopcionService;
        this.adopcionMapper = adopcionMapper;
    }


    @GetMapping
    public List<AdopcionDTO> getAllAdopciones() {
        return adopcionService.getAllAdopciones().stream()
                .map(adopcionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdopcionDTO> getAdopcionById(@PathVariable Long id) {
        return adopcionService.getAdopcionById(id)
                .map(adopcionMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AdopcionDTO> createAdopcion(@RequestBody AdopcionDTO adopcionDTO) {
        try {
            Adopcion adopcion = adopcionMapper.toEntity(adopcionDTO);
            Adopcion adopcionGuardada = adopcionService.saveAdopcion(adopcion);
            return ResponseEntity.status(HttpStatus.CREATED).body(adopcionMapper.toDTO(adopcionGuardada));
        } catch (MascotaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdopcionDTO> updateAdopcion(@PathVariable Long id, @RequestBody AdopcionDTO adopcionDTO) {
        try {
            Adopcion adopcion = adopcionMapper.toEntity(adopcionDTO);
            Adopcion adopcionActualizada = adopcionService.updateAdopcion(id, adopcion);
            return ResponseEntity.ok(adopcionMapper.toDTO(adopcionActualizada));
        } catch (MascotaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdopcion(@PathVariable Long id) {
        try {
            adopcionService.deleteAdopcion(id);
            return ResponseEntity.noContent().build();
        } catch (MascotaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (UsuarioNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
