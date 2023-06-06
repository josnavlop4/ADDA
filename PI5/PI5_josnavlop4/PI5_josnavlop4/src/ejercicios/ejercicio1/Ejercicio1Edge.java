package ejercicios.ejercicio1;

import _datos.DatosEjercicio1;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio1Edge(Ejercicio1Vertex source, Ejercicio1Vertex target, Integer action, Double weight) 
	implements SimpleEdgeAction <Ejercicio1Vertex, Integer> {

	public static Ejercicio1Edge of(Ejercicio1Vertex s, Ejercicio1Vertex t, Integer a) {
		double p = DatosEjercicio1.getBeneficio(s.index())*a;
		return new Ejercicio1Edge(s, t, a, p);
	}
		
	@Override
	public String toString() {
		return String.format("%d; %.1f", action, weight);
	}
}
