package ejercicios.ejercicio1.manual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import _datos.DatosEjercicio1;
import us.lsi.common.List2;

public record Ejercicio1Problem(Integer index, List<Double> remaining) {
	
	public static Ejercicio1Problem of(Integer i, List<Double> rest) {
		return new Ejercicio1Problem(i, rest);
	}
	
	public static Ejercicio1Problem initial() {
		List<Double> lista = new ArrayList<>();
		for(int j=0;j<DatosEjercicio1.getNumTipos();j++) {
			lista.add(DatosEjercicio1.getKgdisp(j).doubleValue());
		}
		return of(0,lista);
	}
	
	public List<Integer> actions() {
		List<Integer> alternativas = new ArrayList<>();
		if(index<DatosEjercicio1.getNumVariedades()) {
			alternativas = List2.rangeList(0, DatosEjercicio1.getMinMax(index,remaining).intValue()+1);
		}
	    return alternativas;
	}
	
	public Ejercicio1Problem neighbor(Integer a) {
		List<Double> res = new ArrayList<>(remaining);
		for(int j = 0; j < remaining.size(); j++) {
			Double kg = DatosEjercicio1.getKgXVariedad(index, j);
			res.set(j, res.get(j) - a*kg);
		}
	    return of(index + 1, res);
	}
	
	public Double heuristic() {
		return IntStream.range(index, DatosEjercicio1.getNumVariedades())
			.filter(i -> DatosEjercicio1.getMinMax(i,remaining)>0)
			.mapToDouble(i -> DatosEjercicio1.getBeneficio(i)*DatosEjercicio1.getMinMax(i,remaining)).sum();
	}
}
