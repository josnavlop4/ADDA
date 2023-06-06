package ejercicios.ejercicio3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import _datos.DatosEjercicio3;
import us.lsi.graphs.virtual.VirtualVertex;

public record Ejercicio3Vertex(Integer index, List<Integer> days, List<List<Integer>> distribucion)
	implements VirtualVertex<Ejercicio3Vertex,Ejercicio3Edge,Integer>{
	
	public static Ejercicio3Vertex of(Integer z, List<Integer> d, List<List<Integer>> dist) {
		return new Ejercicio3Vertex(z, d, dist);
	}
	
	public static Ejercicio3Vertex initial() {
		
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
	
	public static Predicate<Ejercicio3Vertex> goal(){
		return v->v.index() == DatosEjercicio3.getM()*DatosEjercicio3.getN(); 
	}
	
	public static Predicate<Ejercicio3Vertex> goalHasSolution(){
		return v->v.actions().size()==0;
	}
	
	@Override
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

	@Override
	public Ejercicio3Vertex neighbor(Integer a) {
		
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

	@Override
	public Ejercicio3Edge edge(Integer a) {
		return Ejercicio3Edge.of(this, neighbor(a), a);
	}
	
	public Ejercicio3Edge greedyEdge() {
		return null;
	}
}
