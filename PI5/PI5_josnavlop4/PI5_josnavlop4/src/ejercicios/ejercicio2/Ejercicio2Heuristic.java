package ejercicios.ejercicio2;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import _datos.DatosEjercicio2;

public class Ejercicio2Heuristic {
	
	public static Double heuristic(Ejercicio2Vertex v1, Predicate<Ejercicio2Vertex> goal, Ejercicio2Vertex v2) {
		return v1.remaining().isEmpty()? 0.: 
			IntStream.range(v1.index(), DatosEjercicio2.getN())
			.filter( i-> v1.centers().size() < DatosEjercicio2.getMaxC())
			.mapToDouble(i -> DatosEjercicio2.getCoste(i)).min().orElse(100.);
	}
}
