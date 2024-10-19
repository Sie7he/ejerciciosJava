import org.example.servicios.PromedioServicioImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromedioServicioImpTest {

    private PromedioServicioImp promedio = new PromedioServicioImp();

    @Test
    public void calcularPromedioTest() {

        List<Double> notas = Arrays.asList(7.0,5.0,3.0);

        assertEquals(5.0,promedio.calcularPromedio(notas), 0.001, "El promedio debería ser 5.0");
    }

}
