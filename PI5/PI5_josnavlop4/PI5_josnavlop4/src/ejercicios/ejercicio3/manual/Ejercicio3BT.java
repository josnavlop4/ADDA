package ejercicios.ejercicio3.manual;

import java.util.HashSet;
import java.util.Set;

import _soluciones.SolucionEjercicio3;

public class Ejercicio3BT {
	
	private static Double mejorValor;
	private static Ejercicio3State estado;
	private static Set<SolucionEjercicio3> soluciones;
	
	public static void search() {
		soluciones = new HashSet<>();
		mejorValor = Double.MIN_VALUE;
		estado = Ejercicio3State.initial();
		bt_search();
	}
	
	private static void bt_search() {
		if (estado.esSolucion()) {
			Double valorObtenido = estado.acumulado;
			if (valorObtenido > mejorValor) {
				mejorValor=valorObtenido;
				soluciones.add(estado.getSolucion());
			}
		} else if (!estado.esTerminal()) {
			for (Integer a: estado.alternativas()) {
				if (estado.cota(a) >= mejorValor) {
					estado.forward(a);
					bt_search();
					estado.back();
				}
			}
		}
	}
	
	public static Set<SolucionEjercicio3> getSoluciones() {
		return soluciones;
	}

}
