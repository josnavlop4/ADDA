package ejercicios.ejercicio4.manual;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosEjercicio4;
import _soluciones.SolucionEjercicio4;

public class Ejercicio4State {
	
	Ejercicio4Problem actual;
	Double acumulado;
	List<Integer> acciones;
	List<Ejercicio4Problem> anteriores;
	
	private Ejercicio4State(Ejercicio4Problem p, Double a, List<Integer> ls1, List<Ejercicio4Problem> ls2) {
		actual = p;
		acumulado = a;
		acciones = ls1;
		anteriores = ls2;
	}
	
	public static Ejercicio4State initial() {
		Ejercicio4Problem pi = Ejercicio4Problem.initial();
		List<Integer> ls1 = new ArrayList<>();
		List<Ejercicio4Problem> ls2 = new ArrayList<>();
		
		return of(pi,0.,ls1,ls2);
	}

	public static Ejercicio4State of(Ejercicio4Problem prob, Double acum, List<Integer> lsa, List<Ejercicio4Problem> lsp) {
		return new Ejercicio4State(prob, acum, lsa, lsp);
	}
	
	public void forward(Integer a) {
		acumulado += DatosEjercicio4.getBeneficio(actual.neighbor(a).cliente()) - actual.kms() - DatosEjercicio4.getKm(actual.cliente(), a);
		acciones.add(a);
		anteriores.add(actual);
		actual = actual.neighbor(a);
	}
	
	public void back() {
		int prev = acciones.size()-1;
		var last = anteriores.get(prev);

		acumulado -= DatosEjercicio4.getBeneficio(actual.cliente()) + last.kms() + DatosEjercicio4.getKm(last.cliente(), actual.cliente());;
		acciones.remove(prev);
		anteriores.remove(prev);
		actual = last;
	}
	
	public List<Integer> alternativas() {
		return actual.actions();
	}
	
	public Double cota(Integer a) {
		Double weight = DatosEjercicio4.getBeneficio(actual.cliente()) - DatosEjercicio4.getKm(actual.cliente(), actual.neighbor(a).cliente());
		return acumulado + weight + actual.neighbor(a).heuristic();
	}
	
	public Boolean esSolucion() {
		return actual.pendientes().isEmpty();
	}
	
	public Boolean esTerminal() {
		return actual.actions().isEmpty();
	}
	
	
	
	@Override
	public String toString() {
		return "Ejercicio4State [actual=" + actual + ", acumulado=" + acumulado + "]";
	}

	public SolucionEjercicio4 getSolucion() {
		return SolucionEjercicio4.of(acciones);
	}
}
