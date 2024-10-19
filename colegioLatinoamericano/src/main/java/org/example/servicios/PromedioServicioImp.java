package org.example.servicios;

import java.util.List;

public class PromedioServicioImp {

    public double calcularPromedio(List<Double> notas) {
        double promedio = 0;
        for(Double nota : notas) {
            promedio += nota;
        }
        return promedio / notas.size();
    }
}
