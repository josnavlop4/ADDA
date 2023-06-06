package tests.manual;

import java.util.List;

import _datos.DatosEjercicio4;
import _utils.TestsPI5;
import ejercicios.ejercicio4.manual.Ejercicio4BT;
import us.lsi.common.String2;

public class TestEjercicio4BT {

	public static void main(String[] args) {
		List.of(2).forEach(num_test -> {
			DatosEjercicio4.iniDatos("Ejercicio4DatosEntrada"+num_test);
			Ejercicio4BT.search();
			Ejercicio4BT.getSoluciones().forEach(s -> String2.toConsole("Solucion obtenida: %s\n", s));
			TestsPI5.line("*");
		});
		
	}

}
