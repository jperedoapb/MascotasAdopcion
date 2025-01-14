package com.japb.mascotas.application.mapper;

import com.japb.mascotas.application.dto.UsuarioDTO;
import com.japb.mascotas.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId()); //Generalmente no se usa en el POST
        usuario.setNombre(dto.getNombre());
        usuario.setCorreoElectronico(dto.getCorreoElectronico());
        usuario.setTelefono(dto.getTelefono());
        return usuario;
    }
}
