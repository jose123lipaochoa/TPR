package com.example.tpr;

public class Preferencia {
    String id;
    String origen;
    String destino;

    public Preferencia(String id, String origen, String destino) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return this.origen+" - "+this.destino;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
