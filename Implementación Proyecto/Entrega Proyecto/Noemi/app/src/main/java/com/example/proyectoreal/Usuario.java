package com.example.proyectoreal;

public class Usuario {

    String id_reporte;
    String cliente;
    String reporte;
    String tecnico;
    String estado;
    String coordenadas;
    String comentarios;

    public Usuario(String id_reporte, String cliente, String reporte, String tecnico, String estado, String coordenadas, String comentarios) {
        this.id_reporte = id_reporte;
        this.cliente = cliente;
        this.reporte = reporte;
        this.tecnico = tecnico;
        this.estado = estado;
        this.coordenadas = coordenadas;
        this.comentarios = comentarios;
    }

    public String getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(String id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
