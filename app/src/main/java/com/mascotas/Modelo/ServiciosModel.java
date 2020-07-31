package com.mascotas.Modelo;

public class ServiciosModel {
    private String id_servicio;
    private String titulo_servicio, descripcion_servicio, imagen_servicio;
    private String estado, t_ciudad_id_ciudad;

    public ServiciosModel(String id_servicio, String titulo_servicio, String descripcion_servicio, String imagen_servicio, String estado, String t_ciudad_id_ciudad) {
        this.id_servicio = id_servicio;
        this.titulo_servicio = titulo_servicio;
        this.descripcion_servicio = descripcion_servicio;
        this.imagen_servicio = imagen_servicio;
        this.estado = estado;
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }

    public String getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(String id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getTitulo_servicio() {
        return titulo_servicio;
    }

    public void setTitulo_servicio(String titulo_servicio) {
        this.titulo_servicio = titulo_servicio;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public String getImagen_servicio() {
        return imagen_servicio;
    }

    public void setImagen_servicio(String imagen_servicio) {
        this.imagen_servicio = imagen_servicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getT_ciudad_id_ciudad() {
        return t_ciudad_id_ciudad;
    }

    public void setT_ciudad_id_ciudad(String t_ciudad_id_ciudad) {
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }
}