package _soluciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Variedad;
import ejercicios.ejercicio1.Ejercicio1Edge;
import ejercicios.ejercicio1.Ejercicio1Vertex;

public class SolucionEjercicio1 {

	public static SolucionEjercicio1 of(List<Integer> value) {
		return new SolucionEjercicio1(value);
	}
	
	public static SolucionEjercicio1 of(GraphPath<Ejercicio1Vertex, Ejercicio1Edge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		System.out.println(ls);
		SolucionEjercicio1 res = of(ls);
		res.path = ls;
		return res;
	}
	
	private Map<Variedad,Integer> d;
	private Double beneficio;
	
	private List<Integer> path;
	
	private SolucionEjercicio1(List<Integer> value) {
		d = new HashMap<>();
		beneficio = 0.0;
		for (int i=0;i<value.size();i++) {
			Variedad v = DatosEjercicio1.variedades.get(i);
			Integer kgVariedad = value.get(i);
			if(kgVariedad!=0) {
				d.put(v, kgVariedad);
				beneficio+=kgVariedad*v.beneficio();
			}
		}
	}

	@Override
	public String toString() {
		String cad = "Kg que deben producirse para maximizar beneficios: \n";
		for(Variedad v:d.keySet()) {
			cad+=v.nombre()+": "+d.get(v)+" kgs\n";
		}
		cad+="Beneficio: "+beneficio;
		return path==null? cad: String.format("%s\nPath de la solucion: %s", cad, path);
	}
}
