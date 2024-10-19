package org.example.vistas;

import java.util.Scanner;

public abstract class MenuTemplate {


    Scanner sc = new Scanner(System.in);


    public abstract void exportarDatos();
    public abstract void crearAlummno();
    public abstract void agregarMateria();
    public abstract void agregarNotaPasoUno();
    public abstract void listarAlummnos();
    public abstract void terminarPrograma();
    public void iniciarMenu() {
        int opcion;
        do {
            System.out.printf("""
                    Menu
                    1- Listar Alumnos
                    2- Crear alumno
                    3- Agregar Materia
                    4- Agregar Nota Paso Uno
                    5- Exportar Datos
                    6- Terminar Programa
                    Selección:""");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    listarAlummnos();
                    break;
                case 2:
                    crearAlummno();
                    break;
                case 3:
                    agregarMateria();
                    break;
                case 4:
                    agregarNotaPasoUno();
                    break;
                case 5:
                    exportarDatos();
                    break;
                case 6:
                    terminarPrograma();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;

            }
        } while (opcion != 6);
    }
}