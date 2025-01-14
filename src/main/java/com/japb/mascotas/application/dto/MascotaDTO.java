package com.japb.mascotas.application.dto;


import jakarta.validation.constraints.*;

public class MascotaDTO {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 20, message = "La edad no puede ser mayor a 20")
    private Integer edad;

    @NotNull(message = "La disponibilidad es obligatoria")
    private Boolean disponible;

    @NotNull(message = "El tipo de mascota es obligatorio")
    private Long tipoMascotaId;

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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Long getTipoMascotaId() {
        return tipoMascotaId;
    }

    public void setTipoMascotaId(Long tipoMascotaId) {
        this.tipoMascotaId = tipoMascotaId;
    }
}
