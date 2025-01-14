package com.japb.mascotas.application.controller;

import com.japb.mascotas.application.dto.MascotaDTO;
import com.japb.mascotas.application.mapper.MascotaMapper;
import com.japb.mascotas.domain.service.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {


    private final MascotaService mascotaService;


    private final MascotaMapper mascotaMapper;

    public MascotaController(MascotaService mascotaService, MascotaMapper mascotaMapper) {
        this.mascotaService = mascotaService;
        this.mascotaMapper = mascotaMapper;
    }


    @GetMapping
    public List<MascotaDTO> getAllMascotas() {
        return mascotaService.getAllMascotas().stream()
                .map(mascotaMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}") // Nuevo método para buscar por ID
    public ResponseEntity<MascotaDTO> getMascotaById(@PathVariable Long id) {
        return mascotaService.getMascotaById(id)
                .map(mascota -> ResponseEntity.ok(mascotaMapper.toDTO(mascota)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public MascotaDTO createMascota(@RequestBody MascotaDTO dto) {
        var mascota = mascotaMapper.toEntity(dto);
        return mascotaMapper.toDTO(mascotaService.saveMascota(mascota));
    }

    @PutMapping("/{id}")
    public MascotaDTO updateMascota(@PathVariable Long id, @RequestBody MascotaDTO dto) {
        var mascota = mascotaMapper.toEntity(dto);
        return mascotaMapper.toDTO(mascotaService.updateMascota(id, mascota));
    }

    @DeleteMapping("/{id}")
    public void deleteMascota(@PathVariable Long id) {
        mascotaService.deleteMascota(id);
    }
}
