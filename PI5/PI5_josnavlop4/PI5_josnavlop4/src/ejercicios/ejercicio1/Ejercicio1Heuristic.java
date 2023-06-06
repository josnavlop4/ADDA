package ejercicios.ejercicio1;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import _datos.DatosEjercicio1;

public class Ejercicio1Heuristic {
	
	public static Double heuristic(Ejercicio1Vertex v1, Predicate<Ejercicio1Vertex> goal, Ejercicio1Vertex v2) {
		return IntStream.range(v1.index(), DatosEjercicio1.getNumVariedades())
			.filter(i -> DatosEjercicio1.getMinMax(i,v1.remaining())>0)
			.mapToDouble(i -> DatosEjercicio1.getBeneficio(i)*DatosEjercicio1.getMinMax(i,v1.remaining())).sum();
	}
}
