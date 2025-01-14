package com.japb.mascotas.application.mapper;

import com.japb.mascotas.application.dto.AdopcionDTO;
import com.japb.mascotas.domain.model.Adopcion;
import com.japb.mascotas.domain.model.Mascota;
import com.japb.mascotas.domain.model.Usuario;
import com.japb.mascotas.infrastructure.repository.MascotaRepository;
import com.japb.mascotas.infrastructure.repository.UsuarioRepository;
import com.japb.mascotas.utils.MascotaNotFoundException;
import com.japb.mascotas.utils.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdopcionMapper {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AdopcionDTO toDTO(Adopcion adopcion) {
        if (adopcion == null) {
            return null;
        }

        AdopcionDTO dto = new AdopcionDTO();
        dto.setId(adopcion.getId());
        dto.setMascotaId(adopcion.getMascota().getId());
        dto.setUsuarioId(adopcion.getUsuario().getId());
        dto.setFechaAdopcion(adopcion.getFechaAdopcion());
        return dto;
    }

    public Adopcion toEntity(AdopcionDTO dto) {
        if (dto == null) {
            return null;
        }

        Adopcion adopcion = new Adopcion();
        adopcion.setId(dto.getId());

        if (dto.getMascotaId() != null) {
            Optional<Mascota> mascota = mascotaRepository.findById(dto.getMascotaId());
            adopcion.setMascota(mascota.orElseThrow(() -> new MascotaNotFoundException(dto.getMascotaId())));
        } else {
            throw new IllegalArgumentException("El id de la mascota no puede ser nulo");
        }

        if (dto.getUsuarioId() != null) {
            Optional<Usuario> usuario = usuarioRepository.findById(dto.getUsuarioId());
            adopcion.setUsuario(usuario.orElseThrow(() -> new UsuarioNotFoundException(dto.getUsuarioId())));
        } else {
            throw new IllegalArgumentException("El id del usuario no puede ser nulo");
        }
        adopcion.setFechaAdopcion(dto.getFechaAdopcion());

        return adopcion;
    }
}
