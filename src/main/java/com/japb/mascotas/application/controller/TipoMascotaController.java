package com.japb.mascotas.application.controller;

import com.japb.mascotas.application.dto.TipoMascotaDTO;
import com.japb.mascotas.application.mapper.TipoMascotaMapper;
import com.japb.mascotas.domain.service.TipoMascotaService;
import com.japb.mascotas.domain.service.TipoMascotaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos-mascota")
public class TipoMascotaController {


    private final TipoMascotaService tipoMascotaService;
    private final TipoMascotaMapper tipoMascotaMapper;

    public TipoMascotaController(TipoMascotaServiceImpl tipoMascotaService, TipoMascotaMapper tipoMascotaMapper) {
        this.tipoMascotaService = tipoMascotaService;
        this.tipoMascotaMapper = tipoMascotaMapper;
    }


    @GetMapping
    public List<TipoMascotaDTO> getAllTiposMascota() {
        return tipoMascotaService.getAllTiposMascota().stream()
                .map(tipoMascotaMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}") // Nuevo método para buscar por ID
    public ResponseEntity<TipoMascotaDTO> getTipoMascotaById(@PathVariable Long id) {
        return tipoMascotaService.getTipoMascotaById(id)
                .map(tipoMascota -> ResponseEntity.ok(tipoMascotaMapper.toDTO(tipoMascota))) // Si se encuentra, devuelve 200 OK con el DTO
                .orElse(ResponseEntity.notFound().build()); // Si no se encuentra, devuelve 404 Not Found
    }

    @PostMapping
    public TipoMascotaDTO createTipoMascota(@Valid @RequestBody TipoMascotaDTO dto) {
        var tipoMascota = tipoMascotaMapper.toEntity(dto);
        return tipoMascotaMapper.toDTO(tipoMascotaService.saveTipoMascota(tipoMascota));
    }

    @PutMapping("/{id}")
    public TipoMascotaDTO updateTipoMascota(@PathVariable Long id, @Valid @RequestBody TipoMascotaDTO dto) {
        var tipoMascota = tipoMascotaMapper.toEntity(dto);
        return tipoMascotaMapper.toDTO(tipoMascotaService.updateTipoMascota(id, tipoMascota));
    }

    @DeleteMapping("/{id}")
    public void deleteTipoMascota(@PathVariable Long id) {
        tipoMascotaService.deleteTipoMascota(id);
    }
}
