package com.japb.mascotas.infrastructure.repository;

import com.japb.mascotas.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
