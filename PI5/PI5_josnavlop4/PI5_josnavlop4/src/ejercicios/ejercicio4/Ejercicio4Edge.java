package ejercicios.ejercicio4;

import _datos.DatosEjercicio4;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio4Edge(Ejercicio4Vertex source, Ejercicio4Vertex target, Integer action, Double weight) 
	implements SimpleEdgeAction <Ejercicio4Vertex, Integer> {

	public static Ejercicio4Edge of(Ejercicio4Vertex s, Ejercicio4Vertex t, Integer a) {
		return new Ejercicio4Edge(s, t, a, DatosEjercicio4.getBeneficio(t.cliente())-s.kms()-DatosEjercicio4.getKm(s.cliente(), t.cliente()));
	}
	
	@Override
	public String toString() {
		return String.format("%d; %.1f", action, weight);
	}
}


