package com.mascotas.Modelo;

public class FundacionesModel {
    private String id_fundacion;
    private String titulo_fundacion, descripcion_fundacion, imagen_fundacion, correo_fundacion, telefono_fundacion, estado;
    private String id_fundaciones_ciudad, t_fundaciones_id_fundacion, t_ciudad_id_ciudad;

    public FundacionesModel(String id_fundacion, String titulo_fundacion, String descripcion_fundacion, String imagen_fundacion, String correo_fundacion, String telefono_fundacion, String estado, String id_fundaciones_ciudad, String t_fundaciones_id_fundacion, String t_ciudad_id_ciudad) {
        this.id_fundacion = id_fundacion;
        this.titulo_fundacion = titulo_fundacion;
        this.descripcion_fundacion = descripcion_fundacion;
        this.imagen_fundacion = imagen_fundacion;
        this.correo_fundacion = correo_fundacion;
        this.telefono_fundacion = telefono_fundacion;
        this.estado = estado;
        this.id_fundaciones_ciudad = id_fundaciones_ciudad;
        this.t_fundaciones_id_fundacion = t_fundaciones_id_fundacion;
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }

    public String getId_fundacion() {
        return id_fundacion;
    }

    public void setId_fundacion(String id_fundacion) {
        this.id_fundacion = id_fundacion;
    }

    public String getTitulo_fundacion() {
        return titulo_fundacion;
    }

    public void setTitulo_fundacion(String titulo_fundacion) {
        this.titulo_fundacion = titulo_fundacion;
    }

    public String getDescripcion_fundacion() {
        return descripcion_fundacion;
    }

    public void setDescripcion_fundacion(String descripcion_fundacion) {
        this.descripcion_fundacion = descripcion_fundacion;
    }

    public String getImagen_fundacion() {
        return imagen_fundacion;
    }

    public void setImagen_fundacion(String imagen_fundacion) {
        this.imagen_fundacion = imagen_fundacion;
    }

    public String getCorreo_fundacion() {
        return correo_fundacion;
    }

    public void setCorreo_fundacion(String correo_fundacion) {
        this.correo_fundacion = correo_fundacion;
    }

    public String getTelefono_fundacion() {
        return telefono_fundacion;
    }

    public void setTelefono_fundacion(String telefono_fundacion) {
        this.telefono_fundacion = telefono_fundacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId_fundaciones_ciudad() {
        return id_fundaciones_ciudad;
    }

    public void setId_fundaciones_ciudad(String id_fundaciones_ciudad) {
        this.id_fundaciones_ciudad = id_fundaciones_ciudad;
    }

    public String getT_fundaciones_id_fundacion() {
        return t_fundaciones_id_fundacion;
    }

    public void setT_fundaciones_id_fundacion(String t_fundaciones_id_fundacion) {
        this.t_fundaciones_id_fundacion = t_fundaciones_id_fundacion;
    }

    public String getT_ciudad_id_ciudad() {
        return t_ciudad_id_ciudad;
    }

    public void setT_ciudad_id_ciudad(String t_ciudad_id_ciudad) {
        this.t_ciudad_id_ciudad = t_ciudad_id_ciudad;
    }
}