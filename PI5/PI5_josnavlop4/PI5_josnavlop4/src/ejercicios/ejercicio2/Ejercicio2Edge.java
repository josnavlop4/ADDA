package ejercicios.ejercicio2;

import _datos.DatosEjercicio2;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio2Edge(Ejercicio2Vertex source, Ejercicio2Vertex target, Integer action, Double weight) 
	implements SimpleEdgeAction<Ejercicio2Vertex, Integer>{
	
	public static Ejercicio2Edge of(Ejercicio2Vertex s, Ejercicio2Vertex t, Integer a) {
		double p = DatosEjercicio2.getCoste(s.index());
		return new Ejercicio2Edge (s,t,a,a*p);
	}
	
	@Override
	public String toString() {
		return String.format("%d; %.1f", action, weight);
	}

}
