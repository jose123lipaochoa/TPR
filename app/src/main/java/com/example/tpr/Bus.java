package com.example.tpr;

public class Bus {
    String matricula;
    Ruta ruta;
    Integer imagen;
    String Empresa;

    public Bus(String matricula, Ruta ruta, Integer imagen, String empresa) {
        this.matricula = matricula;
        this.ruta = ruta;
        this.imagen = imagen;
        Empresa = empresa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    @Override
    public String toString() {
        return this.matricula;
    }
}

