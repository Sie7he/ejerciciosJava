package org.example.servicios;

import org.example.modelos.Alumno;
import org.example.modelos.Materia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoServicio {

    private Map<String, Alumno> listaAlumnos;

    public Map<String, Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public AlumnoServicio() {
        listaAlumnos = new HashMap<>();

    }

    public void crearAlumno(Alumno alumno) {

        listaAlumnos.put(alumno.getRut(), alumno);
        System.out.println("Alumno Creado");
    }

    public void agregarMateria(String rutAlumno, Materia currentMate) {
        List<Materia> materias = new ArrayList<>();


        for (Map.Entry<String, Alumno> entry : listaAlumnos.entrySet()) {
            if (entry.getValue().getRut().equals(rutAlumno)) {
                List<Materia> materia = entry.getValue().getMateriaList();

                if (materia != null) {
                    materia.add(currentMate);
                } else {
                    materias.add(currentMate);
                    entry.getValue().setMateriaList(materias);
                }
            }
        }
    }

    public List<Materia> materiasPorAlumno(String rut) {

        return  listaAlumnos.get(rut).getMateriaList() != null ?
                listaAlumnos.get(rut).getMateriaList()
                :
                new ArrayList<>();
    }


    public void listarAlumnos() {

        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados\n");
        } else {
            for (Map.Entry<String, Alumno> entry : listaAlumnos.entrySet()) {
                String rut = entry.getKey();
                Alumno alumno = entry.getValue();
                System.out.printf("""
                                --------------------------
                                Datos Alumno 
                                RUT: %s
                                Nombre: %s    
                                Apellido: %s
                                Direccion: %s
                                           
                                """,
                        rut,
                        alumno.getNombre(),
                        alumno.getApellido(),
                        alumno.getDireccion()
                );
                if (alumno.getMateriaList() != null) {
                    System.out.println("Materias");
                    for (Materia materiaList : alumno.getMateriaList()) {
                        System.out.println("     " + materiaList.getNombre());
                        if (materiaList.getNotas() != null) {
                            System.out.println("          Notas:");
                            System.out.println("         " + materiaList.getNotas());
                        }
                    }
                }
            }
        }
    }
}