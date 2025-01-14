package com.japb.mascotas.infrastructure.repository;

import com.japb.mascotas.domain.model.TipoMascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoMascotaRepository extends JpaRepository<TipoMascota, Long> {}
