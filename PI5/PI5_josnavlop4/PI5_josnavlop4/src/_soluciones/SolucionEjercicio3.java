package _soluciones;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;

import _datos.DatosEjercicio3;
import ejercicios.ejercicio3.Ejercicio3Edge;
import ejercicios.ejercicio3.Ejercicio3Vertex;
public class SolucionEjercicio3 implements Comparable<SolucionEjercicio3> {
	
	public Double calidades;
	public List<Integer> res;
	private List<Integer> path;
	private List<List<Integer>> distri;
	
	public static SolucionEjercicio3 of(List<Integer> value) {
		return new SolucionEjercicio3(value);
	}
	
	public static SolucionEjercicio3 of(GraphPath<Ejercicio3Vertex, Ejercicio3Edge> path) {
		return new SolucionEjercicio3(path);
	}
	
	
	
	public SolucionEjercicio3(List<Integer> value) {
		calidades = 0.;
		distri = divideListas(value);
		res = new ArrayList<>();
		
		int m = DatosEjercicio3.getM();
		int e = DatosEjercicio3.getE();
		
		
		System.out.println(distri);
		
		
		for (int j = 0; j<m;j++) {
			Integer dias = 0; 
			List<Integer> va = new ArrayList<>();
			va.addAll(distri.get(j));
			for(int i = 0; i<va.size();i++) {
				dias+=va.get(i);
			}
			Integer diasNecesarios = 0;
			for (int k = 0; k<e; k++) {
				diasNecesarios+=DatosEjercicio3.getDiasNecesarios(j, k);
			}
			if (dias >= diasNecesarios) {
	            calidades += DatosEjercicio3.getCalidadTrbj(j);
	        }
		}
		res.addAll(value);
	}
	
	public static List<List<Integer>> divideListas(List<Integer> value) {
		List<List<Integer>> valores = new ArrayList<>();
	    for (int i = 0; i < value.size(); i += DatosEjercicio3.getM()) {
	        List<Integer> subLista = new ArrayList<>();
	        for (int j = i; j < i + DatosEjercicio3.getM() && j < value.size(); j++) {
	            subLista.add(value.get(j));
	        }
	        valores.add(subLista);
	    }
	    
	    
	    List<List<Integer>> columnas = new ArrayList<>();
	    for (int j = 0; j < DatosEjercicio3.getM(); j++) {
	        List<Integer> columna = new ArrayList<>();
	        for (int i = 0; i < DatosEjercicio3.getN(); i++) {
	            columna.add(valores.get(i).get(j));
	        }
	        columnas.add(columna);
	    }
	    
	    return columnas;
	}
	
	
	public SolucionEjercicio3(GraphPath<Ejercicio3Vertex, Ejercicio3Edge> path) {
		calidades = 0.;
		res = path.getEdgeList().stream().map(e -> e.action()).toList();
		calidades = path.getWeight();
	}


	@Override
	public String toString() {
		List<List<Integer>> valores = obtenerValores(res,DatosEjercicio3.getM());
		String res = "";
		for (int i=0;i<valores.size();i++) {
			System.out.println(DatosEjercicio3.getInvestigadores().get(i).nombre() + ": " + valores.get(i));
		}
			
		res = "calidades: " + calidades;
		return path==null? res: String.format("%s\nPath de la solucion: %s", res, path);
	}
	
	public static List<List<Integer>> obtenerValores(List<Integer> lista, Integer tamSublista) {
	    List<List<Integer>> valores = new ArrayList<>();
	    for (int i = 0; i < lista.size(); i += tamSublista) {
	        List<Integer> subLista = new ArrayList<>();
	        for (int j = i; j < i + tamSublista && j < lista.size(); j++) {
	            subLista.add(lista.get(j));
	        }
	        valores.add(subLista);
	    }
	    return valores;
	}

	@Override
	public int compareTo(SolucionEjercicio3 o) {
		return calidades.compareTo(o.calidades);
	}
}
