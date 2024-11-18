package org.example.vistas;

import org.example.Utils.Util;
import org.example.modelos.Alumno;
import org.example.modelos.Materia;
import org.example.servicios.AlumnoServicio;
import org.example.servicios.ArchivoServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu extends MenuTemplate {

    AlumnoServicio alumnoServicio;
    ArchivoServicio archivoServicio;
    Util util = new Util();

    public Menu() {

        this.alumnoServicio = new AlumnoServicio();
        this.archivoServicio = new ArchivoServicio();
    }

    @Override
    public void exportarDatos() {

        Map<String, Alumno> alumnoMap = alumnoServicio.getListaAlumnos();
        Scanner sc = new Scanner(System.in);
        String path;
        System.out.println("--- Exportar Datos ---");
        System.out.println("Ingresa la ruta en donde se encuentra el archivo notas.csv: ");
        path = sc.nextLine();
        archivoServicio.exportarDatos(alumnoMap, path);


    }

    @Override
    public void crearAlummno() {
        Scanner sc = new Scanner(System.in);

        Alumno alumno = new Alumno();
        System.out.println("Ingresa RUT: ");
        String rut = sc.nextLine();

        if(alumnoServicio.getListaAlumnos().get(rut) == null) {
            alumno.setRut(rut);
            System.out.println("Ingresa Nombre: ");
            alumno.setNombre(sc.nextLine());
            System.out.println("Ingresa Apellido: ");
            alumno.setApellido(sc.nextLine());
            System.out.println("Ingresa Dirección: ");
            alumno.setDireccion(sc.nextLine());

            alumnoServicio.crearAlumno(alumno);
        } else {
            System.out.println("Este alumno ya existe");
        }


    }

    @Override
    public void agregarMateria() {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Agregar Materia ---");
        System.out.println("Ingresa el Rut del alumno: ");
        String rut = sc.nextLine();
        if(alumnoServicio.getListaAlumnos().get(rut) == null) {
            System.out.println("Alumno no encontrado");
            return;
        }

        util.materiasNoAsignadas(rut, alumnoServicio);

    }

    @Override
    public void agregarNotaPasoUno() {
        Map<String, Alumno> alumnos = alumnoServicio.getListaAlumnos();
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa rut del alumno");
        String rut = sc.nextLine();
        Alumno alumno = alumnos.get(rut);
        if(alumno == null) {
            System.out.println("Alumno no encontrado");
            return;
        }

        List<Materia> materiaList = alumno.getMateriaList();

        if(materiaList == null) {
            System.out.println("El alumno no tiene materias agregadas");

        } else {
            System.out.println("Alumno tiene las siguientes materias agregadas:");
            for(int i = 0; i <materiaList.size(); i++) {
                System.out.println((i+1) + ". " + materiaList.get(i).getNombre());
            }
            try {
                System.out.print("\n Seleccionar Materia: ");
                int materia = sc.nextInt();

                System.out.print("\n Ingresar nota: ");

                double nota = sc.nextDouble();

            List<Double> notas = materiaList.get(materia - 1).getNotas();
            if(notas == null){
                notas = new ArrayList<>();
                materiaList.get(materia-1).setNotas(notas);
            }
            notas.add(nota);
            System.out.println("--- ¡Nota agregada a "+ materiaList.get(materia-1).getNombre() + " !---\n");

            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: "+ e);
            }
        }

    }

    @Override
    public void listarAlummnos() {
    alumnoServicio.listarAlumnos();

    }

    @Override
    public void terminarPrograma() {

    }
}
