package com.japb.mascotas.domain.service;
import com.japb.mascotas.domain.model.Mascota;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MascotaService {
    List<Mascota> getAllMascotas();
    Mascota saveMascota(Mascota mascota);
    Mascota updateMascota(Long id, Mascota mascota);
    void deleteMascota(Long id);
    Optional<Mascota> getMascotaById(Long id);
}
