package tests;

import java.util.List;

import _datos.DatosEjercicio1;
import _soluciones.SolucionEjercicio1;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicios.ejercicio1.Ejercicio1Vertex;

public class TestEjercicio1 {

	public static void main(String[] args) {
		
		List.of(1,2,3).forEach(num_test -> {
			TestsPI5.iniTest("Ejercicio1DatosEntrada", num_test, DatosEjercicio1::iniDatos);
			
			// TODO Defina en el tipo vertice un m. factoria para el vertice inicial
			// TODO Defina en el tipo vertice un m. static / Predicate para los vertices finales 
			TestsPI5.tests(
				Ejercicio1Vertex.initial(),		// Vertice Inicial
				Ejercicio1Vertex.goal(),			// Predicado para un vertice final
				GraphsPI5::multisetBuilder,			// Referencia al Builder del grafo
				Ejercicio1Vertex::greedyEdge,	// Referencia a la Funcion para la arista voraz
				SolucionEjercicio1::of);			// Referencia al metodo factoria para la solucion
		});
		

	}

}
