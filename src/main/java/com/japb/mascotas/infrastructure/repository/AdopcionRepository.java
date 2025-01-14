package com.japb.mascotas.infrastructure.repository;

import com.japb.mascotas.domain.model.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopcionRepository extends JpaRepository<Adopcion, Long> {}
