package com.mascotas.Modelo;

public class AdoptaVidaModel {
    private String id_adopta_vida;
    private String titulo_adopta_vida, descripcion_adopta_vida, imagen_adopta_vida, correo_adopta_vida, telefono_adopta_vida, estado;
    private String id_adopta_vida_ciudad, t_adopta_vida_id_adopta_vida, t_ciudad_id_ciudad;

    public AdoptaVidaModel(String id_adopta_vida, String titulo_adopta_vida, String descripcion_adopta_vida, String imagen_adopta_vida, String correo_adopta_vida, String telefono_adopta_vida, String estado, String id_adopta_vida_ciudad, String t_adopta_vida_id_adopta_vida, String t_ciudad_id_ciudad) {
        this.id_adopta_vida = id_adopta_vida;
        this.titulo_adopta_vida = titulo_adopta_vida;
        this.descripcion_adopta_vida = descripcion_adopta_vida;
        this.imagen_adopta_vida = imagen_adopta_vida;
        this.correo_adopta_vida = correo_adopta_vida;
        this.telefono_adopta_vida = telefono_adopta_vida;
        this.estado = estado;
        this.id_adopta_vida_ciudad = id_adopta_vida_ciudad;
        this.t_adopta_vida_id_adopta_vida = t_adopta_vida_id_adopta_vida;
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }

    public String getId_adopta_vida() {
        return id_adopta_vida;
    }

    public void setId_adopta_vida(String id_adopta_vida) {
        this.id_adopta_vida = id_adopta_vida;
    }

    public String getTitulo_adopta_vida() {
        return titulo_adopta_vida;
    }

    public void setTitulo_adopta_vida(String titulo_adopta_vida) {
        this.titulo_adopta_vida = titulo_adopta_vida;
    }

    public String getDescripcion_adopta_vida() {
        return descripcion_adopta_vida;
    }

    public void setDescripcion_adopta_vida(String descripcion_adopta_vida) {
        this.descripcion_adopta_vida = descripcion_adopta_vida;
    }

    public String getImagen_adopta_vida() {
        return imagen_adopta_vida;
    }

    public void setImagen_adopta_vida(String imagen_adopta_vida) {
        this.imagen_adopta_vida = imagen_adopta_vida;
    }

    public String getCorreo_adopta_vida() {
        return correo_adopta_vida;
    }

    public void setCorreo_adopta_vida(String correo_adopta_vida) {
        this.correo_adopta_vida = correo_adopta_vida;
    }

    public String getTelefono_adopta_vida() {
        return telefono_adopta_vida;
    }

    public void setTelefono_adopta_vida(String telefono_adopta_vida) {
        this.telefono_adopta_vida = telefono_adopta_vida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_adopta_vida_ciudad() {
        return id_adopta_vida_ciudad;
    }

    public void setId_adopta_vida_ciudad(String id_adopta_vida_ciudad) {
        this.id_adopta_vida_ciudad = id_adopta_vida_ciudad;
    }

    public String getT_adopta_vida_id_adopta_vida() {
        return t_adopta_vida_id_adopta_vida;
    }

    public void setT_adopta_vida_id_adopta_vida(String t_adopta_vida_id_adopta_vida) {
        this.t_adopta_vida_id_adopta_vida = t_adopta_vida_id_adopta_vida;
    }

    public String getT_ciudad_id_ciudad() {
        return t_ciudad_id_ciudad;
    }

    public void setT_ciudad_id_ciudad(String t_ciudad_id_ciudad) {
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }
}