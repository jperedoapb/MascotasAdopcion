package com.japb.mascotas.infrastructure.repository;

import com.japb.mascotas.domain.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {}
