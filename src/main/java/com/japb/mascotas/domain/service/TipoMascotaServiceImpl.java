package com.japb.mascotas.domain.service;

import com.japb.mascotas.domain.model.TipoMascota;
import com.japb.mascotas.infrastructure.repository.TipoMascotaRepository;
import com.japb.mascotas.utils.TipoMascotaConcurrenteException;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMascotaServiceImpl implements TipoMascotaService{

    private final TipoMascotaRepository tipoMascotaRepository;

    public TipoMascotaServiceImpl(TipoMascotaRepository tipoMascotaRepository) {
        this.tipoMascotaRepository = tipoMascotaRepository;
    }

    public List<TipoMascota> getAllTiposMascota() {
        return tipoMascotaRepository.findAll();
    }

    public TipoMascota saveTipoMascota(TipoMascota tipoMascota) {
        try{
            return tipoMascotaRepository.saveAndFlush(tipoMascota);
        }
        catch (ObjectOptimisticLockingFailureException e){
            throw new TipoMascotaConcurrenteException("Otro Usuario ha modificado el Tipo de Mascota");
        }
    }

    public TipoMascota updateTipoMascota(Long id, TipoMascota tipoMascota) {
        var existingTipoMascota = tipoMascotaRepository.findById(id).orElseThrow();
        existingTipoMascota.setNombre(tipoMascota.getNombre());
        return tipoMascotaRepository.save(existingTipoMascota);
    }

    public void deleteTipoMascota(Long id) {
        tipoMascotaRepository.deleteById(id);
    }

    @Override
    public Optional<TipoMascota> getTipoMascotaById(Long id) {
        return tipoMascotaRepository.findById(id);
    }
}
