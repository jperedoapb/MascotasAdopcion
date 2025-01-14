package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.Adopcion;
import com.japb.mascotas.infrastructure.repository.AdopcionRepository;
import com.japb.mascotas.utils.AdopcionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionServiceImpl implements AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    @Override
    public List<Adopcion> getAllAdopciones() {
        return adopcionRepository.findAll();
    }

    @Override
    public Adopcion saveAdopcion(Adopcion adopcion) {
        return adopcionRepository.save(adopcion);
    }

    @Override
    public Optional<Adopcion> getAdopcionById(Long id) {
        return adopcionRepository.findById(id);
    }

    @Override
    public void deleteAdopcion(Long id) {
        adopcionRepository.deleteById(id);
    }

    @Override
    public Adopcion updateAdopcion(Long id, Adopcion adopcion) {
        return adopcionRepository.findById(id)
                .map(existingAdopcion -> {
                    existingAdopcion.setMascota(adopcion.getMascota());
                    existingAdopcion.setUsuario(adopcion.getUsuario());
                    existingAdopcion.setFechaAdopcion(adopcion.getFechaAdopcion());
                    return adopcionRepository.save(existingAdopcion);
                }).orElseThrow(() -> new AdopcionNotFoundException(id));
    }
}
