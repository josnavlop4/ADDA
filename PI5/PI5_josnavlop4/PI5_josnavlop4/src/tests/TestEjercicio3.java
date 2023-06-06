package tests;

import java.util.List;

import _datos.DatosEjercicio3;
import _soluciones.SolucionEjercicio3;
import _utils.GraphsPI5;
import _utils.TestsPI5;
import ejercicios.ejercicio3.Ejercicio3Vertex;

public class TestEjercicio3 {

	public static void main(String[] args) {
		
		List.of(1,2,3).forEach(num_test -> {
			TestsPI5.iniTest("Ejercicio3DatosEntrada", num_test, DatosEjercicio3::iniDatos);
			
			// TODO Defina en el tipo vertice un m. factoria para el vertice inicial
			// TODO Defina en el tipo vertice un m. static / Predicate para los vertices finales 
			TestsPI5.tests(Ejercicio3Vertex.initial(),		// Vertice Inicial
				Ejercicio3Vertex.goal(),			// Predicado para un vertice final
				GraphsPI5::multisetBuilder,			// Referencia al Builder del grafo
				Ejercicio3Vertex::greedyEdge,	// Referencia a la Funcion para la arista voraz
				SolucionEjercicio3::of);			// Referencia al metodo factoria para la solucion
		});

	}

}
