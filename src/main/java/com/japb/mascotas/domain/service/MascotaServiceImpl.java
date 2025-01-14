package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.Mascota;
import com.japb.mascotas.infrastructure.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {


    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota saveMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota updateMascota(Long id, Mascota mascota) {
        var existingMascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
        existingMascota.setNombre(mascota.getNombre());
        existingMascota.setEdad(mascota.getEdad());
        existingMascota.setDisponible(mascota.getDisponible());
        existingMascota.setTipoMascota(mascota.getTipoMascota());
        return mascotaRepository.save(existingMascota);
    }

    @Override
    public void deleteMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    public Optional<Mascota> getMascotaById(Long id) {
        return mascotaRepository.findById(id);
    }
}
