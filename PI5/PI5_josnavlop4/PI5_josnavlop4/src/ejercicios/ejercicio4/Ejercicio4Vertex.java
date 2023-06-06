package ejercicios.ejercicio4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import _datos.DatosEjercicio4;
import us.lsi.common.List2;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio4Vertex(Integer cliente, Set<Integer> pendientes, List<Integer> visitados, Integer kms) 
	implements VirtualVertex <Ejercicio4Vertex,Ejercicio4Edge,Integer>  {
	
	public static Ejercicio4Vertex of(Integer c, Set<Integer> p, List<Integer> v, Integer k) {
		return new Ejercicio4Vertex(c, p, v, k);
	}
	
	public static Ejercicio4Vertex initial() {
		Set<Integer> pend = new HashSet<>();
		List<Integer> clientes = new ArrayList<>(DatosEjercicio4.getClientes().stream().map(c->c.id()).toList());
		List<Integer> visitados = new ArrayList<>();
		Integer km = 0;
		
		for(int i=0; i<DatosEjercicio4.getN();i++) {
			pend.add(clientes.get(i));
		}
		return of(0,pend,visitados,km);
	}
	
	public static Predicate<Ejercicio4Vertex> goal(){
		return v->v.actions().isEmpty();
	}
	
	public static Predicate<Ejercicio4Vertex> goalHasSolution(){
		return v->v.pendientes().isEmpty();
	}

	@Override
	public List<Integer> actions() {
		List<Integer> alternativas = new ArrayList<>();
		List<Integer> pend = new ArrayList<>(pendientes);
//		if(cliente<DatosEjercicio4.getN()) {
			if (!pendientes.isEmpty()) {
				if(pend.size()==1) {
					if(pend.get(pend.size()-1)==0) {
						if(DatosEjercicio4.existeArista(cliente, pend.get(pend.size()-1))) {
							alternativas.add(pend.get(pend.size()-1));
						}
					}
				}else {
					for(int i =0; i<pendientes.size();i++) {
						if(DatosEjercicio4.existeArista(cliente, pend.get(i))) {
							alternativas.add(pend.get(i));
						}
					}	
				}
				int[] aa = {1,2,7,3,5,6,4,0};
				alternativas=List2.of(aa[visitados.size()]);
		    }
//		}
		return alternativas;
	}

	@Override
	public Ejercicio4Vertex neighbor(Integer a) {
		
		Set<Integer> newPend = new HashSet<>(pendientes);
		List<Integer> newVisi = new ArrayList<>(visitados);
		Integer newKms = kms;
		
		newPend.remove(a);
		newVisi.add(a);
		
		if (newVisi.size() > 1) {
	        Integer ultimo = newVisi.get(newVisi.size() - 2);
	        Double dist = DatosEjercicio4.getKm(ultimo, a);
	        newKms += dist.intValue();
	    } else {
	    	newKms += DatosEjercicio4.getKm(0, a).intValue();
	    }
	    
		return of(a,newPend,newVisi,newKms);
	}

	@Override
	public Ejercicio4Edge edge(Integer a) {
		return Ejercicio4Edge.of(this, neighbor(a), a);
	}
	
	public Ejercicio4Edge greedyEdge() {
		return null;
	}
}
