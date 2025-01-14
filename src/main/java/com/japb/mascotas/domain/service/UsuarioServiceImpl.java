package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.Usuario;
import com.japb.mascotas.infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario usuarioActualizado = existingUsuario.get();
            usuarioActualizado.setNombre(usuario.getNombre());
            usuarioActualizado.setCorreoElectronico(usuario.getCorreoElectronico());
            usuarioActualizado.setTelefono(usuario.getTelefono());
            return usuarioRepository.save(usuarioActualizado);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id); //Lanzar excepción personalizada sería mejor
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }
}
