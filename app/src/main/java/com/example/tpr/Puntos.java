package com.example.tpr;

public class Puntos {
    String id;
    Integer order;
    Double latitud;
    Double longitud;

    public Puntos(String id, Integer order, Double latitud, Double longitud) {
        this.id = id;
        this.order = order;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
