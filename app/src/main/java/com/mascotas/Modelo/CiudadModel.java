package com.mascotas.Modelo;

public class CiudadModel {
    private String id_ciudad,ciudad,imagen_ciudad,estado;

    public CiudadModel(String id_ciudad, String ciudad, String imagen_ciudad, String estado) {
        this.id_ciudad = id_ciudad;
        this.ciudad = ciudad;
        this.imagen_ciudad = imagen_ciudad;
        this.estado = estado;
    }

    public String getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(String id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getImagen_ciudad() {
        return imagen_ciudad;
    }

    public void setImagen_ciudad(String imagen_ciudad) {
        this.imagen_ciudad = imagen_ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
