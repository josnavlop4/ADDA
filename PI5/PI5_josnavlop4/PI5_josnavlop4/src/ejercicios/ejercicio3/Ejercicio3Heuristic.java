package ejercicios.ejercicio3;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import _datos.DatosEjercicio3;

public class Ejercicio3Heuristic {
	
	public static Double heuristic(Ejercicio3Vertex v1, Predicate<Ejercicio3Vertex> goal, Ejercicio3Vertex v2) {
		return v1.days().isEmpty()? 0.:IntStream.range(v1.index(), DatosEjercicio3.getN()*DatosEjercicio3.getM())
		        .mapToDouble(z -> DatosEjercicio3.getCalidadTrbj(z%DatosEjercicio3.getM())).sum();
	}

}
