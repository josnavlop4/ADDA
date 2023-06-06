package _soluciones;

import java.util.List;
import java.util.stream.Collectors;

import _datos.DatosEjercicio2;
import _datos.DatosEjercicio2.Curso;
import us.lsi.common.List2;

public class SolucionEjercicio2 {
	
	public static SolucionEjercicio2 create(List<Integer> ls) {
		return new SolucionEjercicio2(ls);
	}
	
	private Double total;
	private List<Curso> cursos;
	
	private SolucionEjercicio2(List<Integer> ls) {
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
				return String.format("%sCoste Total: %.1f", s, total);
	}
	
	

}
