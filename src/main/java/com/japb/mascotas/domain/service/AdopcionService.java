package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.Adopcion;

import java.util.List;
import java.util.Optional;

public interface AdopcionService {
    List<Adopcion> getAllAdopciones();
    Adopcion saveAdopcion(Adopcion adopcion);
    Optional<Adopcion> getAdopcionById(Long id);
    void deleteAdopcion(Long id);
    Adopcion updateAdopcion(Long id, Adopcion adopcion);
}
