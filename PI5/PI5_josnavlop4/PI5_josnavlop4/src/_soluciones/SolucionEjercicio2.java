package _soluciones;

import java.util.List;
import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import _datos.DatosEjercicio2;
import _datos.DatosEjercicio2.Curso;
import ejercicios.ejercicio2.Ejercicio2Edge;
import ejercicios.ejercicio2.Ejercicio2Vertex;
import us.lsi.common.List2;

public class SolucionEjercicio2 {
	
	public static SolucionEjercicio2 of(List<Integer> ls) {
		return new SolucionEjercicio2(ls);
	}
	
	public static SolucionEjercicio2 of(GraphPath<Ejercicio2Vertex, Ejercicio2Edge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		SolucionEjercicio2 res = of(ls);
		res.path = ls;
		return res;
	}
	
	private List<Integer> path;
	
	private Double total;
	private List<Curso> cursos;
	
	private SolucionEjercicio2(List<Integer> ls) {
		System.out.println(ls);
		total = 0.0;
		cursos = List2.empty();
		for (int i=0;i<ls.size();i++) {
			if(ls.get(i)>0) {
				total+=DatosEjercicio2.getCoste(i);
				cursos.add(DatosEjercicio2.getCursos(i));
			}
		}
	}

	@Override
	public String toString() {
		String s = cursos.stream().map(e -> "S"+e.id())
				.collect(Collectors.joining(", ", "Cursos elegidos: {", "}\n"));
		String res = String.format("%sCoste Total: %.1f", s, total);
		
		return path==null? res: String.format("%s\nPath de la solucion: %s", res, path);		
	}
	
	

}
