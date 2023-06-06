package ejercicios.ejercicio2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import _datos.DatosEjercicio2;
import us.lsi.common.Set2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio2Vertex(Integer index, Set<Integer> remaining, Set<Integer> centers) 
	implements VirtualVertex<Ejercicio2Vertex, Ejercicio2Edge, Integer>{
	
	public static Ejercicio2Vertex of(Integer i, Set<Integer> rest, Set<Integer> ce) {
		return new Ejercicio2Vertex(i, rest,ce);
	}
	
	public static Ejercicio2Vertex initial() {
		return of(0,Set2.copy(DatosEjercicio2.getTematica()),Set2.empty());
	}
	
	public static Predicate<Ejercicio2Vertex> goal(){
		return v->v.index() == DatosEjercicio2.getN(); 
	}
	
	public static Predicate<Ejercicio2Vertex> goalHasSolution(){
		return v->v.remaining.isEmpty();
	}
	
	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = new ArrayList<>();

	    if (index < DatosEjercicio2.getN()) {
	        if(remaining.isEmpty()) {
	        	alternativas.add(0);
	        } else {
	        	if(centers.size()<DatosEjercicio2.getMaxC() || centers.contains(DatosEjercicio2.getCentroXCurso(index))){
	        		alternativas = List.of(0,1);
	        	}else {
	        		alternativas.add(0);
	        	}
	        } 
	    }
	    return alternativas;
	}

	@Override
	public Ejercicio2Vertex neighbor(Integer a) {
		Set<Integer> rest = a==0? Set2.copy(remaining):Set2.difference(remaining, DatosEjercicio2.getTematicaXCurso(index));
		Set<Integer> cent = new HashSet<>(centers);
		if(a==1) {
			cent.add(DatosEjercicio2.getCentroXCurso(index));
		}
		return of(index+1,rest,cent);
	}

	@Override
	public Ejercicio2Edge edge(Integer a) {
		return Ejercicio2Edge.of(this, neighbor(a), a);
	}
	
	public Ejercicio2Edge greedyEdge() {
		return null;
	}
}