package com.example.mabisrror.javafundamentalsforandroid.clase02;

/**
 * Created by mabisrror on 1/4/17.
 */



public abstract class Forma {

    public double ancho;
    public double alto;

    public Forma(double ancho, double alto) {
        this.ancho = ancho;
        this.alto = alto;
    }

    public abstract double calcularArea();

}



