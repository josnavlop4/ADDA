package ejercicios.ejercicio3.manual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import _datos.DatosEjercicio3;

public record Ejercicio3Problem(Integer index, List<Integer> days, List<List<Integer>> distribucion) {
	
	public static Ejercicio3Problem of(Integer z, List<Integer> d, List<List<Integer>> dist) {
		return new Ejercicio3Problem(z, d, dist);
	}
	
	public static Ejercicio3Problem initial() {
		
		List<Integer> d = new ArrayList<>();
		List<List<Integer>> dist = new ArrayList<>();
		
		for(int i=0;i<DatosEjercicio3.getN();i++) {
			d.add(DatosEjercicio3.getDiasDispl(i));
		}
		for(int j=0;j<DatosEjercicio3.getM();j++) {
			List<Integer> especialidades = new ArrayList<>();
			for(int k=0;k<DatosEjercicio3.getE();k++) {
				especialidades.add(DatosEjercicio3.getDiasNecesarios(j, k));
			}
			dist.add(especialidades);
		}
		
		return of(0,d,dist);
	}
	
	public List<Integer> actions() {
		List<Integer> alternativas = new ArrayList<>();
		
		Integer m =	DatosEjercicio3.getM();
		Integer n = DatosEjercicio3.getN();
		
		
		if(index<(m*n)) {
			Integer i = index/m;
			Integer j = index%m;
			Integer k = DatosEjercicio3.getEspInv(i);
			
			for(int a=0;a<=days.get(i) && a<=distribucion.get(j).get(k);a++) {
				alternativas.add(a);
			}
		}
		return alternativas;
	}

	public Ejercicio3Problem neighbor(Integer a) {
		
		Integer m =	DatosEjercicio3.getM();
		Integer i = index/m;
		Integer j = index%m;
		Integer k = DatosEjercicio3.getEspInv(i);
		
		List<List<Integer>> dist = new ArrayList<>();
		for(List<Integer> dis : distribucion) {
			dist.add(new ArrayList<>(dis));
		}
		
		List<Integer> d = new ArrayList<>(days);
		
		d.set(i, days.get(i)-a);
		dist.get(j).set(k, dist.get(j).get(k)-a);
		
		return of(index+1,d,dist);
	}
	
	public Double heuristic() {
		return IntStream.range(index, DatosEjercicio3.getN()*DatosEjercicio3.getM())
		        .mapToDouble(z -> DatosEjercicio3.getCalidadTrbj(z%DatosEjercicio3.getM())).max().orElse(-10.);
	}

}
