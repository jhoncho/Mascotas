package com.mascotas.Modelo;

public class MascotasEncuentraModel {
    private String id_encuentra_mascota,titulo_encuentra_mascota,descripcion_encuentra_mascota,imagen_encuentra_mascota,correo_encuentra_mascota,telefono_encuentra_mascota,estado,id_encuentra_mascota_ciudad,t_encuentra_mascota_id_encuentra_mascota,t_ciudad_id_ciudad;

    public MascotasEncuentraModel(String id_encuentra_mascota, String titulo_encuentra_mascota, String descripcion_encuentra_mascota, String imagen_encuentra_mascota, String correo_encuentra_mascota, String telefono_encuentra_mascota, String estado, String id_encuentra_mascota_ciudad, String t_encuentra_mascota_id_encuentra_mascota, String t_ciudad_id_ciudad) {
        this.id_encuentra_mascota = id_encuentra_mascota;
        this.titulo_encuentra_mascota = titulo_encuentra_mascota;
        this.descripcion_encuentra_mascota = descripcion_encuentra_mascota;
        this.imagen_encuentra_mascota = imagen_encuentra_mascota;
        this.correo_encuentra_mascota = correo_encuentra_mascota;
        this.telefono_encuentra_mascota = telefono_encuentra_mascota;
        this.estado = estado;
        this.id_encuentra_mascota_ciudad = id_encuentra_mascota_ciudad;
        this.t_encuentra_mascota_id_encuentra_mascota = t_encuentra_mascota_id_encuentra_mascota;
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }

    public String getId_encuentra_mascota() {
        return id_encuentra_mascota;
    }

    public void setId_encuentra_mascota(String id_encuentra_mascota) {
        this.id_encuentra_mascota = id_encuentra_mascota;
    }

    public String getTitulo_encuentra_mascota() {
        return titulo_encuentra_mascota;
    }

    public void setTitulo_encuentra_mascota(String titulo_encuentra_mascota) {
        this.titulo_encuentra_mascota = titulo_encuentra_mascota;
    }

    public String getDescripcion_encuentra_mascota() {
        return descripcion_encuentra_mascota;
    }

    public void setDescripcion_encuentra_mascota(String descripcion_encuentra_mascota) {
        this.descripcion_encuentra_mascota = descripcion_encuentra_mascota;
    }

    public String getImagen_encuentra_mascota() {
        return imagen_encuentra_mascota;
    }

    public void setImagen_encuentra_mascota(String imagen_encuentra_mascota) {
        this.imagen_encuentra_mascota = imagen_encuentra_mascota;
    }

    public String getCorreo_encuentra_mascota() {
        return correo_encuentra_mascota;
    }

    public void setCorreo_encuentra_mascota(String correo_encuentra_mascota) {
        this.correo_encuentra_mascota = correo_encuentra_mascota;
    }

    public String getTelefono_encuentra_mascota() {
        return telefono_encuentra_mascota;
    }

    public void setTelefono_encuentra_mascota(String telefono_encuentra_mascota) {
        this.telefono_encuentra_mascota = telefono_encuentra_mascota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_encuentra_mascota_ciudad() {
        return id_encuentra_mascota_ciudad;
    }

    public void setId_encuentra_mascota_ciudad(String id_encuentra_mascota_ciudad) {
        this.id_encuentra_mascota_ciudad = id_encuentra_mascota_ciudad;
    }

    public String getT_encuentra_mascota_id_encuentra_mascota() {
        return t_encuentra_mascota_id_encuentra_mascota;
    }

    public void setT_encuentra_mascota_id_encuentra_mascota(String t_encuentra_mascota_id_encuentra_mascota) {
        this.t_encuentra_mascota_id_encuentra_mascota = t_encuentra_mascota_id_encuentra_mascota;
    }

    public String getT_ciudad_id_ciudad() {
        return t_ciudad_id_ciudad;
    }

    public void setT_ciudad_id_ciudad(String t_ciudad_id_ciudad) {
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }
}
