package ejercicios.ejercicio3;

import _datos.DatosEjercicio3;
import us.lsi.graphs.virtual.SimpleEdgeAction;

public record Ejercicio3Edge(Ejercicio3Vertex source, Ejercicio3Vertex target, Integer action, Double weight) 
implements SimpleEdgeAction<Ejercicio3Vertex, Integer> {

	public static Ejercicio3Edge of(Ejercicio3Vertex s, Ejercicio3Vertex t, Integer a) {
		
		Integer j = s.index()%DatosEjercicio3.getM();
		Integer i = s.index()/DatosEjercicio3.getM();
		
		double p = 0.;
		Boolean realizado = true;
		if(i==(DatosEjercicio3.getN()-1)) {
			
			for(int k=0; k<DatosEjercicio3.getE();k++) {
				if(t.distribucion().get(j).get(k)!=0) {
					realizado=false;
					break;
				}
			}
			if (realizado) {
				p = DatosEjercicio3.getCalidadTrbj(j);
			}
		}
		
		return new Ejercicio3Edge (s,t,a,p);
	}
	
	@Override
	public String toString() {
		return String.format("%d; %.1f", action, weight);
	}

}
