package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.TipoMascota;

import java.util.List;
import java.util.Optional;

public interface TipoMascotaService {
    List<TipoMascota> getAllTiposMascota();
    TipoMascota saveTipoMascota(TipoMascota tipoMascota);
    TipoMascota updateTipoMascota(Long id, TipoMascota tipoMascota);
    void deleteTipoMascota(Long id);
    Optional<TipoMascota> getTipoMascotaById(Long id);
}
