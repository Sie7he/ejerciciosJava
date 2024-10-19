
import org.example.modelos.Alumno;
import org.example.modelos.Materia;
import org.example.modelos.MateriaEnum;
import org.example.servicios.AlumnoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AlumnoServicioTest {

    @InjectMocks
    private AlumnoServicio alumnoServicio;

    @Mock
    private AlumnoServicio alumnoServicioMock;

    private MateriaEnum matematicas;
    private MateriaEnum lenguaje;
    private Alumno mapu;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        matematicas = MateriaEnum.MATEMATICAS;
        lenguaje = MateriaEnum.LEGUAJE;
        List<Materia> materiaList = new ArrayList<>();

        Materia mate = new Materia(matematicas);
        Materia len = new Materia(lenguaje);

        materiaList.add(mate);
        materiaList.add(len);

        mapu = new Alumno("1", "Alumno", "prueba", "dirección", materiaList);

    }


    @Test
    public void agregarAlumnoTest() {
        alumnoServicio.crearAlumno(mapu);

        Alumno alumnoRecuperado = alumnoServicio.getListaAlumnos().get(mapu.getRut());
        assertNotNull(alumnoRecuperado, "El alumno debería haber sido creado correctamente");
        assertEquals("Alumno", alumnoRecuperado.getNombre(), "El nombre del alumno no coincide");
    }

    @Test
    public void agregarMateriaTest() {

        alumnoServicio.crearAlumno(mapu);
        Materia historia = new Materia(MateriaEnum.HISTORIA);

        alumnoServicio.agregarMateria(mapu.getRut(), historia);
        List<Materia> materias = mapu.getMateriaList();
        assertTrue(materias.contains(historia));

    }

    @Test
    public void listarAlumnosTest() {
        alumnoServicio.crearAlumno(mapu);

        assertNotNull(alumnoServicio.getListaAlumnos(), "La lista no deberia ser null");
        assertEquals(mapu, alumnoServicio.getListaAlumnos().get("1"), "El alumno no coincide con Mapu");
        assertEquals(alumnoServicio.getListaAlumnos().size(), 1, "El tamaño de la lista debería ser 1");
    }

    @Test
    public void materiasPorAlumnoTest() {

        List<Materia> materiasMock = Arrays.asList(
                new Materia(matematicas),
                new Materia(lenguaje)
        );

        String rut = "2";
        when(alumnoServicioMock.materiasPorAlumno(rut)).thenReturn(materiasMock);
        List<Materia> materiasRecuperadas = alumnoServicioMock.materiasPorAlumno(rut);

        assertEquals(2, materiasRecuperadas.size(), "El alumno debería tener 2 materias asignadas");
        assertEquals(matematicas, materiasRecuperadas.get(0).getNombre());
        assertEquals(lenguaje, materiasRecuperadas.get(1).getNombre());


    }
}
