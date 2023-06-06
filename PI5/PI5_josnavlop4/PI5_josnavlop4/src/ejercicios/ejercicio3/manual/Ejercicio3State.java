package ejercicios.ejercicio3.manual;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosEjercicio3;
import _soluciones.SolucionEjercicio3;

public class Ejercicio3State {
	
	Ejercicio3Problem actual;
	Double acumulado;
	List<Integer> acciones;
	List<Ejercicio3Problem> anteriores;
	
	private Ejercicio3State(Ejercicio3Problem p, Double a, List<Integer> ls1, List<Ejercicio3Problem> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}
	
	public static Ejercicio3State initial() {
		Ejercicio3Problem pi = Ejercicio3Problem.initial();
		return of(pi,0.,new ArrayList<>(),new ArrayList<>());
	}

	public static Ejercicio3State of(Ejercicio3Problem prob, Double acum, List<Integer> lsa, List<Ejercicio3Problem> lsp) {
		return new Ejercicio3State(prob, acum, lsa, lsp);
	}
	
	public static Integer trabajoCompletado(Ejercicio3Problem s, Ejercicio3Problem t) {
		Integer conteoS = 0;
		Integer conteoT = 0;
		Integer j = s.index()%DatosEjercicio3.getM();
		
		conteoT = t.distribucion().get(j).stream().mapToInt(i->i).sum();
		conteoS = s.distribucion().get(j).stream().mapToInt(i->i).sum();
		
		return conteoT==0 && conteoS!=0?1:0;
	}
	
	public void forward(Integer a) {
		acumulado += DatosEjercicio3.getCalidadTrbj(actual.index()%DatosEjercicio3.getM()) * trabajoCompletado(actual, actual.neighbor(a));
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}
	
	public void back() {
		int last = acciones.size()-1;
		var prob_ant = anteriores.get(last);

		acumulado -= DatosEjercicio3.getCalidadTrbj(prob_ant.index()%DatosEjercicio3.getM()) * trabajoCompletado(prob_ant, actual) ;
		acciones.remove(last);
		anteriores.remove(last);
		actual = prob_ant;
	}
	
	public List<Integer> alternativas() {
		return actual.actions();
	}
	
	public Double cota(Integer a) {
		Integer j = actual.index()%DatosEjercicio3.getM();
		
		return acumulado + DatosEjercicio3.getCalidadTrbj(j) * trabajoCompletado(actual, actual.neighbor(a))  + actual.neighbor(a).heuristic();
	}
	
	public Boolean esSolucion() {
		return actual.index() == DatosEjercicio3.getM()*DatosEjercicio3.getN();
	}
	
	public Boolean esTerminal() {
		return actual.index() == DatosEjercicio3.getM()*DatosEjercicio3.getN();
	}
	
	public SolucionEjercicio3 getSolucion() {
		return SolucionEjercicio3.of(acciones);
	}
}
