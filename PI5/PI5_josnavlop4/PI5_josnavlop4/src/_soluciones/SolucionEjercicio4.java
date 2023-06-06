package _soluciones;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;

import _datos.Cliente;
import _datos.DatosEjercicio4;
import ejercicios.ejercicio4.Ejercicio4Edge;
import ejercicios.ejercicio4.Ejercicio4Vertex;

public class SolucionEjercicio4 implements Comparable<SolucionEjercicio4> {

	public static SolucionEjercicio4 of(List<Integer> ls) {
		return new SolucionEjercicio4(ls);
	}
	
	public static SolucionEjercicio4 of(GraphPath<Ejercicio4Vertex, Ejercicio4Edge> path) {
		List<Integer> ls = path.getEdgeList().stream().map(e -> e.action()).toList();
		SolucionEjercicio4 res = of(ls);
		res.path = ls;
		return res;
	}
	
	private Double kms;
    private Double beneficio;
    private List<Cliente> clientes;
    
    private List<Integer> path;
	
	public SolucionEjercicio4(List<Integer> ls) {
		System.out.println(ls);
		kms = 0.0;
	    beneficio = 0.0;
	    clientes = new ArrayList<>();

	    if (DatosEjercicio4.existeArista(0, ls.get(0))) {
	        kms += DatosEjercicio4.getKm(0, ls.get(0));
	        beneficio += DatosEjercicio4.getBeneficio(ls.get(0)) - kms;
	    }

	    for (int i = 0; i < ls.size() - 1; i++) {
	        int current = ls.get(i);
	        int next = ls.get(i + 1);
	        clientes.add(DatosEjercicio4.getV(current));
	        if (DatosEjercicio4.existeArista(current, next)) {
	            kms += DatosEjercicio4.getKm(current, next);
	            beneficio += DatosEjercicio4.getBeneficio(next) - kms;
	        }
	    }

	    if (ls.size() > 0) {
	        clientes.add(DatosEjercicio4.getV(ls.get(ls.size() - 1)));
	    }
	}

	@Override
	public String toString() {
		String res = "SolucionEjercicio4 [kms=" + kms + ", beneficio=" + beneficio + ", clientes=" + clientes + "]";
		return path==null? res: String.format("%s\nPath de la solucion: %s", res, path);
	}

	@Override
	public int compareTo(SolucionEjercicio4 o) {
		return beneficio.compareTo(o.beneficio);
	}

}
