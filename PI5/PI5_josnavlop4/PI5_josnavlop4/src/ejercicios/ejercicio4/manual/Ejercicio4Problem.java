package ejercicios.ejercicio4.manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import _datos.DatosEjercicio4;
import us.lsi.common.List2;

public record Ejercicio4Problem(Integer cliente, Set<Integer> pendientes, List<Integer> visitados, Integer kms) {
	
	public static Ejercicio4Problem of(Integer c, Set<Integer> p, List<Integer> v, Integer k) {
		return new Ejercicio4Problem(c, p, v, k);
	}
	
	public static Ejercicio4Problem initial() {
		Set<Integer> pend = new HashSet<>();
		List<Integer> clientes = new ArrayList<>(DatosEjercicio4.getClientes().stream().map(c->c.id()).toList());
		List<Integer> visitados = new ArrayList<>();
		Integer km = 0;
		
		for(int i=0; i<DatosEjercicio4.getN();i++) {
			pend.add(clientes.get(i));
		}
		
		return of(0,pend,visitados,km);
	}
	
	public List<Integer> actions() {
		List<Integer> alternativas = new ArrayList<>();
		List<Integer> pend = new ArrayList<>(pendientes);
		if (!pendientes.isEmpty()) {
			if(pend.size()==1) {
				if(pend.get(pend.size()-1)==0) {
					if(DatosEjercicio4.existeArista(cliente, pend.get(pend.size()-1))) {
						alternativas.add(pend.get(pend.size()-1));
					}
				}
			}else {
				for(int i = 0; i<pendientes.size();i++) {
					if(pend.get(i)!=0) {
						if(DatosEjercicio4.existeArista(cliente, pend.get(i))) {
							alternativas.add(pend.get(i));
						}
					}	
				}	
			}
//			int[] aa = {2,5,3,7,4,6,1,0};
//			alternativas=List2.of(aa[visitados.size()]);
	    } 
		return alternativas;
	}

	public Ejercicio4Problem neighbor(Integer a) {
		
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
	
	public Double heuristic() {
		double p = 0.;
		for (int i=0; i<pendientes.size();i++) {
			p += DatosEjercicio4.getBeneficio(pendientes.stream().toList().get(i)) - kms;
		}
		return p;
		
	}

}
