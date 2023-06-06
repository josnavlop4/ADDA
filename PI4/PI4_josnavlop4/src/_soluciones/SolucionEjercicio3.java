package _soluciones;

import java.util.ArrayList;
import java.util.List;

import _datos.DatosEjercicio3;
public class SolucionEjercicio3 {
	
	public Double calidades;
	public List<Integer> res;
	
	public static SolucionEjercicio3 of(List<Integer> value) {
		return new SolucionEjercicio3(value);
	}
	
	public SolucionEjercicio3(List<Integer> value) {
		calidades = 0.;
		res = new ArrayList<>();
		System.out.println(value);
		
		int n = DatosEjercicio3.getN();
		int m = DatosEjercicio3.getM();
		int e = DatosEjercicio3.getE();
		
		for(int j = 0; j<m ;j++) {
			Boolean realizado = true;
			
			for(int k = 0; k<e ;k++) {
				int diasInvertEspecialidad = 0;
				for(int i = 0; i<n ;i++) {
					int diasInvertidos = value.get((j*n)+i);
					if(DatosEjercicio3.isEspXTrabajador(i, k)==1) {
						diasInvertEspecialidad+=diasInvertidos;
					}
				}
				if(diasInvertEspecialidad!=DatosEjercicio3.getDiasNecesarios(j,k)) {
					realizado=false;
					break;
				}
			}
			if(realizado) {
				calidades+=DatosEjercicio3.getCalidadTrbj(j);
			}
		}
		res.addAll(value);
	
	}


	@Override
	public String toString() {
		List<List<Integer>> valores = obtenerValores(res,DatosEjercicio3.getN());
		for (int i=0;i<valores.size();i++) {
			System.out.println(DatosEjercicio3.getTrabajos().get(i).nombre() + ": " + valores.get(i));
		}
			
		
		return "calidades: " + calidades;
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
}
