package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Usuario saveUsuario(Usuario usuario);
    Usuario updateUsuario(Long id, Usuario usuario);
    void deleteUsuario(Long id);
    Optional<Usuario> getUsuarioById(Long id);
}
