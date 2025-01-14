package com.japb.mascotas.application.mapper;

import com.japb.mascotas.application.dto.TipoMascotaDTO;
import com.japb.mascotas.domain.model.TipoMascota;
import org.springframework.stereotype.Component;

@Component
public class TipoMascotaMapper {

    public TipoMascotaDTO toDTO(TipoMascota tipoMascota) {
        var dto = new TipoMascotaDTO();
        dto.setId(tipoMascota.getId());
        dto.setNombre(tipoMascota.getNombre());
        return dto;
    }

    public TipoMascota toEntity(TipoMascotaDTO dto) {
        var tipoMascota = new TipoMascota();
        tipoMascota.setId(dto.getId());
        tipoMascota.setNombre(dto.getNombre());
        return tipoMascota;
    }
}
