package com.japb.mascotas.utils;

public class MascotaNotFoundException extends RuntimeException {
    public MascotaNotFoundException(Long id) {
        super("Mascota no encontrada con ID: " + id);
    }
}
