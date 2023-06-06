package ejercicios.ejercicio2.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import _datos.DatosEjercicio2;
import us.lsi.common.Set2;

public record Ejercicio2Problem(Integer index, Set<Integer> remaining, Set<Integer> centers) {
	
	public static Ejercicio2Problem of(Integer i, Set<Integer> rest, Set<Integer> ce) {
		return new Ejercicio2Problem(i, rest,ce);
	}
	
	public static Ejercicio2Problem initial() {
		return of(0,Set2.copy(DatosEjercicio2.getTematica()),Set2.empty());
	}
	
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

	public Ejercicio2Problem neighbor(Integer a) {
		Set<Integer> rest = a==0? Set2.copy(remaining):Set2.difference(remaining, DatosEjercicio2.getTematicaXCurso(index));
		Set<Integer> cent = new HashSet<>(centers);
		if(a==1) {
			cent.add(DatosEjercicio2.getCentroXCurso(index));
		}
		return of(index+1,rest,cent);
	}
	
	public Double heuristic() {
		return remaining.isEmpty()? 0.: 
			IntStream.range(index, DatosEjercicio2.getN())
			.filter( i-> centers.size() < DatosEjercicio2.getMaxC())
			.mapToDouble(i -> DatosEjercicio2.getCoste(i)).min().orElse(100.);
	}

}
