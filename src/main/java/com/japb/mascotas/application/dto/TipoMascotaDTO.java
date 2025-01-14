package com.japb.mascotas.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TipoMascotaDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio") // No puede ser nulo ni vacío
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres") // Longitud entre 3 y 50
    private String nombre;

    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
