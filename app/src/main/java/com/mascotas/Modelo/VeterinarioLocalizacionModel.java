package com.mascotas.Modelo;

public class VeterinarioLocalizacionModel {
    private String id_veterinario, titulo_veterinario, correo_veterinario, telefono_veterinario,latitud, longitud;

    public VeterinarioLocalizacionModel(String id_veterinario, String titulo_veterinario, String correo_veterinario, String telefono_veterinario, String latitud, String longitud) {
        this.id_veterinario = id_veterinario;
        this.titulo_veterinario = titulo_veterinario;
        this.correo_veterinario = correo_veterinario;
        this.telefono_veterinario = telefono_veterinario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(String id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public String getTitulo_veterinario() {
        return titulo_veterinario;
    }

    public void setTitulo_veterinario(String titulo_veterinario) {
        this.titulo_veterinario = titulo_veterinario;
    }

    public String getCorreo_veterinario() {
        return correo_veterinario;
    }

    public void setCorreo_veterinario(String correo_veterinario) {
        this.correo_veterinario = correo_veterinario;
    }

    public String getTelefono_veterinario() {
        return telefono_veterinario;
    }

    public void setTelefono_veterinario(String telefono_veterinario) {
        this.telefono_veterinario = telefono_veterinario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
