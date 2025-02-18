package com.japb.mascotas.application.mapper;

import com.japb.mascotas.application.dto.MascotaDTO;
import com.japb.mascotas.domain.model.Mascota;
import com.japb.mascotas.domain.model.TipoMascota;
import com.japb.mascotas.infrastructure.repository.TipoMascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MascotaMapper {
    @Autowired
    private TipoMascotaRepository tipoMascotaRepository;
    public Mascota toEntity(MascotaDTO dto) {
        Mascota mascota = new Mascota();
        mascota.setId(dto.getId());
        mascota.setNombre(dto.getNombre());
        mascota.setEdad(dto.getEdad());
        mascota.setDisponible(dto.getDisponible());
        if (dto.getTipoMascotaId() != null) {
            Optional<TipoMascota> tipoMascotaOptional = tipoMascotaRepository.findById(dto.getTipoMascotaId());
            if (tipoMascotaOptional.isPresent()) {
                mascota.setTipoMascota(tipoMascotaOptional.get());
            } else {
                // Manejo de error si no se encuentra el TipoMascota
                throw new RuntimeException("No se encontró el TipoMascota con ID: " + dto.getTipoMascotaId());
            }
        } else {
            // Manejo de error si el tipoMascotaId es nulo
            throw new RuntimeException("El tipoMascotaId no puede ser nulo");
        }
        return mascota;
    }

    public MascotaDTO toDTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEdad(mascota.getEdad());
        dto.setDisponible(mascota.getDisponible());
        dto.setTipoMascotaId(mascota.getTipoMascota().getId());
        return dto;
    }
}
