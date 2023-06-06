package ejercicios.ejercicio4;

import java.util.function.Predicate;

import _datos.DatosEjercicio4;

public class Ejercicio4Heuristic {
	
	public static Double heuristic(Ejercicio4Vertex v1, Predicate<Ejercicio4Vertex> goal, Ejercicio4Vertex v2) {
		
		double p = 0.;
		for (int i=0; i<v1.pendientes().size();i++) {
			p += DatosEjercicio4.getBeneficio(v1.pendientes().stream().toList().get(i));
		}
		return p;
	}

}
