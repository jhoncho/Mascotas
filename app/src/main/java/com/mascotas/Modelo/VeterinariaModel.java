package com.mascotas.Modelo;

public class VeterinariaModel {
    private String id_veterinario;
    private String titulo_veterinario, descripcion_veterinario, imagen_veterinario, correo_veterinario, telefono_veterinario, estado;
    private String id_veterinario_ciudad, t_veterinarios_id_veterinario, t_ciudad_id_ciudad;

    public VeterinariaModel(String id_veterinario, String titulo_veterinario, String descripcion_veterinario, String imagen_veterinario, String correo_veterinario, String telefono_veterinario, String estado, String id_veterinario_ciudad, String t_veterinarios_id_veterinario, String t_ciudad_id_ciudad) {
        this.id_veterinario = id_veterinario;
        this.titulo_veterinario = titulo_veterinario;
        this.descripcion_veterinario = descripcion_veterinario;
        this.imagen_veterinario = imagen_veterinario;
        this.correo_veterinario = correo_veterinario;
        this.telefono_veterinario = telefono_veterinario;
        this.estado = estado;
        this.id_veterinario_ciudad = id_veterinario_ciudad;
        this.t_veterinarios_id_veterinario = t_veterinarios_id_veterinario;
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
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

    public String getDescripcion_veterinario() {
        return descripcion_veterinario;
    }

    public void setDescripcion_veterinario(String descripcion_veterinario) {
        this.descripcion_veterinario = descripcion_veterinario;
    }

    public String getImagen_veterinario() {
        return imagen_veterinario;
    }

    public void setImagen_veterinario(String imagen_veterinario) {
        this.imagen_veterinario = imagen_veterinario;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_veterinario_ciudad() {
        return id_veterinario_ciudad;
    }

    public void setId_veterinario_ciudad(String id_veterinario_ciudad) {
        this.id_veterinario_ciudad = id_veterinario_ciudad;
    }

    public String getT_veterinarios_id_veterinario() {
        return t_veterinarios_id_veterinario;
    }

    public void setT_veterinarios_id_veterinario(String t_veterinarios_id_veterinario) {
        this.t_veterinarios_id_veterinario = t_veterinarios_id_veterinario;
    }

    public String getT_ciudad_id_ciudad() {
        return t_ciudad_id_ciudad;
    }

    public void setT_ciudad_id_ciudad(String t_ciudad_id_ciudad) {
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }
}