package tests;

import java.util.List;

import _datos.DatosEjercicio2;
import _soluciones.SolucionEjercicio2;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicios.ejercicio2.Ejercicio2Vertex;

public class TestEjercicio2 {

	public static void main(String[] args) {
		List.of(1,2,3).forEach(num_test -> {
			TestsPI5.iniTest("Ejercicio2DatosEntrada", num_test, DatosEjercicio2::iniDatos);
			
			// TODO Defina en el tipo vertice un m. factoria para el vertice inicial
			// TODO Defina en el tipo vertice un m. static / Predicate para los vertices finales 
			TestsPI5.tests(
				Ejercicio2Vertex.initial(),		// Vertice Inicial
				Ejercicio2Vertex.goal(),			// Predicado para un vertice final
				GraphsPI5::multisetBuilder,			// Referencia al Builder del grafo
				Ejercicio2Vertex::greedyEdge,	// Referencia a la Funcion para la arista voraz
				SolucionEjercicio2::of);			// Referencia al metodo factoria para la solucion
		});

	}

}
