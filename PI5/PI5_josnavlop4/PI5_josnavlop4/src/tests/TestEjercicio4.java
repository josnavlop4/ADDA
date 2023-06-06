package tests;

import java.util.List;

import _datos.DatosEjercicio4;
import _soluciones.SolucionEjercicio4;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicios.ejercicio4.Ejercicio4Vertex;

public class TestEjercicio4 {

	public static void main(String[] args) {
		
		List.of(2).forEach(num_test -> {
			TestsPI5.iniTest2("Ejercicio4DatosEntrada", num_test, DatosEjercicio4::iniDatos);
			
			// TODO Defina en el tipo vertice un m. factoria para el vertice inicial
			// TODO Defina en el tipo vertice un m. static / Predicate para los vertices finales 
			TestsPI5.tests(
				Ejercicio4Vertex.initial(),		// Vertice Inicial
				Ejercicio4Vertex.goal(),			// Predicado para un vertice final
				GraphsPI5::multisetBuilder,			// Referencia al Builder del grafo
				Ejercicio4Vertex::greedyEdge,	// Referencia a la Funcion para la arista voraz
				SolucionEjercicio4::of);			// Referencia al metodo factoria para la solucion
		});

	}

}
