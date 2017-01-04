package com.example.mabisrror.javafundamentalsforandroid.clase02;

/**
 * Created by mabisrror on 1/4/17.
 */
class Triangulo extends Forma {

    public Triangulo(double ancho, double alto) {
        super(ancho, alto);
    }

    @Override
    public double calcularArea() {
        return (ancho*alto)/2;
    }
}
