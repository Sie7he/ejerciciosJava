package org.example.servicios;

import org.example.modelos.Alumno;
import org.example.modelos.Materia;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ArchivoServicio {

    private List<Alumno> alumnosACargar;
    private PromedioServicioImp promedioServicioImp;

    public ArchivoServicio(){}

    public ArchivoServicio(List<Alumno> alumnosACargar, PromedioServicioImp promedioServicioImp) {
        this.alumnosACargar = alumnosACargar;
        this.promedioServicioImp = promedioServicioImp;
    }

    public void exportarDatos(Map<String, Alumno> alumnoMap, String path) {

        String nombre = "alumnos.txt";

        try {
            FileWriter archivo = new FileWriter(path+nombre);
            PrintWriter pw = new PrintWriter(archivo);
            for (Map.Entry<String,Alumno> entry : alumnoMap.entrySet()) {
                pw.print("Rut: ");
                pw.println(entry.getValue().getRut());
                pw.print("Nombre: ");
                pw.println(entry.getValue().getNombre());
                pw.print("Apellido: ");
                pw.println(entry.getValue().getApellido());
                pw.print("Dirección: ");
                pw.println(entry.getValue().getDireccion());
                pw.println("Asignaturas");
                if(entry.getValue().getMateriaList() != null){
                    for(Materia materia: entry.getValue().getMateriaList()) {
                        pw.println(". "+ materia.getNombre());
                        if(materia.getNotas() != null) {
                            pw.print("Promedio: ");
                            pw.println(promedioServicioImp.calcularPromedio(materia.getNotas()));
                        }
                    }
                }
                pw.println("--------------------------");
            }
            pw.close();
            archivo.close();
            System.out.println("Datos exportados correctamente.");
        } catch (Exception e) {
            System.out.println("No se pudo crear el archivo:"+ nombre +" por el siguiente error: "+e);
        }

    }
}
