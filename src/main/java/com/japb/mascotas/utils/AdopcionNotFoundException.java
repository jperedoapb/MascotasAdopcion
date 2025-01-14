package com.japb.mascotas.utils;

public class AdopcionNotFoundException extends RuntimeException {
    public AdopcionNotFoundException(Long id) {
        super("Adopción no encontrada con ID: " + id);
    }
}