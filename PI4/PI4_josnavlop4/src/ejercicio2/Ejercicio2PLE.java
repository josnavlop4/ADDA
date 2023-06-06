package ejercicio2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


import _datos.DatosEjercicio2;
import _datos.DatosEjercicio2.Curso;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio2PLE {
	
	public static List<Curso> cursos;
	public static Integer maxCentros;
	
	public static Integer getN() {
		return cursos.size();
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
	
	public static void ejercicio2_model() throws IOException {
		DatosEjercicio2.iniDatos("ficheros/Ejercicio2DatosEntrada3.txt");

		cursos = DatosEjercicio2.cursos;
		maxCentros = DatosEjercicio2.maxCentros;
		
		//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
		AuxGrammar.generate(Ejercicio2PLE.class,"lsi_models/Ejercicio2.lsi","gurobi_models/Ejercicio2-3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio2-3.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		
	}
	
	public static void main(String[] args) throws IOException {
		ejercicio2_model();
	}

}
