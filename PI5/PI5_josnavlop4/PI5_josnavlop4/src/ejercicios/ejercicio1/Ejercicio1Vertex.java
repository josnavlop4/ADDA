package ejercicios.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import _datos.DatosEjercicio1;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio1Vertex(Integer index, List<Double> remaining) 
	implements VirtualVertex <Ejercicio1Vertex,Ejercicio1Edge,Integer> {
	
	public static Ejercicio1Vertex of(Integer i, List<Double> rest) {
		return new Ejercicio1Vertex(i, rest);
	}
	
	public static Ejercicio1Vertex initial() {
		List<Double> lista = new ArrayList<>();
		for(int j=0;j<DatosEjercicio1.getNumTipos();j++) {
			lista.add(Double.valueOf(DatosEjercicio1.getKgdisp(j)));
		}
		return of(0,lista);
	}
	
	public static Predicate<Ejercicio1Vertex> goal(){
		return v -> v.index() == DatosEjercicio1.getNumVariedades();
	}
	
	public static Predicate<Ejercicio1Vertex> goalHasSolution(){
		return v -> v.index() == DatosEjercicio1.getNumVariedades();
	}
	

	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = List2.empty();
		if(index<DatosEjercicio1.getNumVariedades()) {
				alternativas = List2.rangeList(0, DatosEjercicio1.getMinMax(index,remaining).intValue()+1);
		}
	    return alternativas;
	}

	@Override
	public Ejercicio1Vertex neighbor(Integer a) {
		List<Double> res = new ArrayList<>(remaining);
		for(int j = 0; j < remaining.size(); j++) {
			Double kg = DatosEjercicio1.getKgXVariedad(index, j);
			res.set(j, res.get(j) - a*kg);
		}
	    return of(index + 1, res);
	}

	@Override
	public Ejercicio1Edge edge(Integer a) {
		return Ejercicio1Edge.of(this, neighbor(a), a);
	}
	
	public Ejercicio1Edge greedyEdge() {
		return null;
	}
}
