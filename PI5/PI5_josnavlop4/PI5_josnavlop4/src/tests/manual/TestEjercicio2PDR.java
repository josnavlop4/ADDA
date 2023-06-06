package tests.manual;

import java.util.List;

import _datos.DatosEjercicio2;
import _utils.TestsPI5;
import ejercicios.ejercicio2.manual.Ejercicio2PDR;
import us.lsi.common.String2;

public class TestEjercicio2PDR {

	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada"+num_test+".txt");
			String2.toConsole("Solucion obtenida: %s\n", Ejercicio2PDR.search());
			TestsPI5.line("*");
		});
	}

}
