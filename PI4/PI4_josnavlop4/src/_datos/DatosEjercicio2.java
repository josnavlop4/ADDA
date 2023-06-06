package _datos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import us.lsi.common.Files2;
import us.lsi.common.List2;
import us.lsi.common.Set2;

public class DatosEjercicio2 {
	
	public record Curso (Integer id, Set<Integer> tematicas, Double coste, Integer centro) {
		public static int cont;
		public static Curso create(String s) {
			String[] v = s.split(":");
			return new Curso(cont++, Set2.parse(v[0].trim(), "{,}" , Integer::parseInt),
				Double.parseDouble(v[1].trim()),Integer.parseInt(v[2].trim()));
		}
	}
	
	public static List<Curso> cursos;
	public static Integer maxCentros;
	
	public static void iniDatos(String fichero) {
		Curso.cont=0;
		cursos = List2.empty();
		Set<Integer> temats = new TreeSet<>();
		
		List<String> lineas = Files2.linesFromFile(fichero);
		maxCentros =  Integer.parseInt(lineas.get(0).replace("Max_Centros = ", ""));
		lineas.remove(0);
		
		for(String linea: lineas) {
			Curso s = Curso.create(linea);
			cursos.add(s);
			temats.addAll(s.tematicas());
		}
		System.out.println("cursos: "+cursos);
		System.out.println("Max_Centros:"+maxCentros);
	}
	
	public static Integer getN() {
		return cursos.size();
	}
	public static Curso getCursos(Integer i) {
		return cursos.get(i);
	}
	public static Set<Integer> getTematica() {
		Set<Integer> res = new HashSet<>();
		for (Curso c:cursos) {
			res.addAll(c.tematicas());
		}
		return res;
	}
	public static Integer getM() {
		return getTematica().size();
	}
	public static Set<Integer> getCentros() {
		Set<Integer> res = new HashSet<>();
		for (Curso c:cursos) {
			res.add(c.centro());
		}
		return res;
	}
	public static Integer getCentroXCurso(Integer i) {
		return cursos.get(i).centro();
	}
	public static Integer getNC() {
		return getCentros().size();
	}
	public static Integer getMaxC() {
		return maxCentros;
	}
	public static Set<Integer> getTematicaXCurso(Integer i){
		return cursos.get(i).tematicas();
	}
	public static Integer getNTC(Integer i) {
		return getTematicaXCurso(i).size();
	}
	public static Integer contieneTematica(Integer i, Integer j) {
		return cursos.get(i).tematicas().contains(getTematica().toArray()[j])?1:0;
	}
	public static Double getCoste(Integer i) {
		return cursos.get(i).coste();
	}
	public static Integer getCentroOferCurso(Integer i,Integer k) {
		return cursos.get(i).centro().equals(getCentros().toArray()[k])?1:0;
	}
		
	
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio2DatosEntrada2.txt");
	}
}
