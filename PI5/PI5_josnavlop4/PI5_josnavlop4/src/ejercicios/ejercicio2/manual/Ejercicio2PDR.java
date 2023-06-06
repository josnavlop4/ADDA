package ejercicios.ejercicio2.manual;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import _datos.DatosEjercicio2;
import _soluciones.SolucionEjercicio2;
import us.lsi.common.List2;
import us.lsi.common.Map2;

public class Ejercicio2PDR {
	
	public static record Spm (Integer a, Integer weight) implements Comparable<Spm> {
		
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a,weight);
		}

		@Override
		public int compareTo(Spm o) {
			return this.weight.compareTo(o.weight);
		}
	}
	
	public static Map<Ejercicio2Problem, Spm> memory;
	public static Integer mejorValor;
	
	public static SolucionEjercicio2 search() {
		memory = Map2.empty();
		mejorValor=Integer.MAX_VALUE;
		
		pdr_search(Ejercicio2Problem.initial(),0,memory);
		return getSolucion();
	}

	private static Spm pdr_search(Ejercicio2Problem prob, Integer acumulado, Map<Ejercicio2Problem, Spm> memory2) {
		Spm res = null;
		Boolean esTerminal = prob.index().equals(DatosEjercicio2.getN());
		Boolean esSolucion = prob.remaining().isEmpty();
		
		if(memory.containsKey(prob)) {
			res = memory.get(prob);
		} else if (esTerminal && esSolucion) {
			res = Spm.of(null,0);
			memory.put(prob, res);
			if(acumulado < mejorValor) {
				mejorValor = acumulado;
			}
		} else {
			List<Spm> soluciones = List2.empty();
			for(Integer action : prob.actions()) {
				Double cota = acotar(acumulado,prob,action);
				if(cota > mejorValor) {
					continue;
				}
				Ejercicio2Problem vecino = prob.neighbor(action);
				Spm s= pdr_search(vecino, acumulado + action, memory);
				if(s!=null) {
					Spm amp = Spm.of(action, s.weight() + action);
					soluciones.add(amp);
				}
			}
			res = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
			if(res!=null) memory.put(prob, res);
		}
		return res;
	}
	
	
	private static Double acotar(Integer acum, Ejercicio2Problem p, Integer a) {
		return acum + a + p.neighbor(a).heuristic();
	}

	private static SolucionEjercicio2 getSolucion() {
		List<Integer> acciones = List2.empty();
		Ejercicio2Problem prob = Ejercicio2Problem.initial();
		Spm spm = memory.get(prob);
		while (spm!=null && spm.a!=null) {
			Ejercicio2Problem old = prob;
			acciones.add(spm.a);
			prob=old.neighbor(spm.a);
			spm = memory.get(prob);
		}
		return SolucionEjercicio2.of(acciones);
	}

}
