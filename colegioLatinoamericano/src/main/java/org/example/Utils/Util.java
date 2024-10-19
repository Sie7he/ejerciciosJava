package org.example.Utils;

import org.example.modelos.Materia;
import org.example.modelos.MateriaEnum;
import org.example.servicios.AlumnoServicio;

import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class Util {

    Scanner sc = new Scanner(System.in);


    public List<MateriaEnum> filtrarMaterias(String rut, AlumnoServicio alumnoServicio) {
        List<Materia> materiaAlumno = alumnoServicio.materiasPorAlumno(rut);

        List<MateriaEnum> materiasAlumnos = materiaAlumno.stream()
                .map(Materia::getNombre)
                .toList();

        List<MateriaEnum> materiasDisponibles = EnumSet.allOf(MateriaEnum.class).stream()
                .filter(materia -> !materiasAlumnos.contains(materia))
                .toList();

        return materiasDisponibles;
    }




    public void materiasNoAsignadas(String rut, AlumnoServicio alumnoServicio) {

        List<MateriaEnum> materiasDisponibles = filtrarMaterias(rut, alumnoServicio);

        System.out.println("Materias Disponibles");
        if(materiasDisponibles.size() > 0) {
            for(int i = 0; i < materiasDisponibles.size(); i++ ) {
                System.out.println((i+1) + ". " + materiasDisponibles.get(i));
            }
            try {
                int indice = sc.nextInt();
                sc.nextLine();
                MateriaEnum materiaSeleccionada = materiasDisponibles.get(indice-1);
                alumnoServicio.agregarMateria(rut, new Materia(materiaSeleccionada));
                System.out.println("---Materia Agregada---");
            } catch (Exception e) {
                System.out.println("Por favor, elija una materia disponible");
            }

        } else {
            System.out.println("Todas las materias han sido agregadas");
        }



    }


}