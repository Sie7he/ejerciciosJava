package org.example.modelos;

import java.util.List;

public class Materia {

    private MateriaEnum nombre;
    private List<Double> notas;

    public Materia(){}

    public Materia(MateriaEnum nombre, List notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public Materia(MateriaEnum nombre) {
        this.nombre = nombre;

    }

    public MateriaEnum getNombre() {
        return nombre;
    }

    public void setNombre(MateriaEnum nombre) {
        this.nombre = nombre;
    }

    public List getNotas() {
        return notas;
    }

    public void setNotas(List notas) {
        this.notas = notas;
    }
}
