package com.example.mabisrror.javafundamentalsforandroid.clase02;

/**
 * Created by mabisrror on 1/4/17.
 */

public class TestForma {

    public static void main(String args[]) {
        Triangulo triangulo = new Triangulo(10,5);
        Rectangulo rectangulo = new Rectangulo(10,20);

        System.out.println(triangulo.calcularArea());

        System.out.println(rectangulo.calcularArea());
    }

}
