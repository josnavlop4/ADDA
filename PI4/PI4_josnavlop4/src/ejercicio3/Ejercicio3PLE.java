package ejercicio3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosEjercicio3;
import _datos.DatosEjercicio3.Investigador;
import _datos.DatosEjercicio3.Trabajo;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio3PLE {
	
	public static List<Investigador> investigadores;
	public static List<Trabajo> trabajos;
	public static List<Integer> especialidades;

	public static Integer getN() {
		return investigadores.size();
	}
	public static Integer getM() {
		return trabajos.size();
	}
	public static Integer getE() {
		return especialidades.size();
	}
	public static Integer isEspXTrabajador(Integer i, Integer k) {
		return investigadores.get(i).especialidad().equals(k)?1:0;
	}
	public static Integer getDiasDispl(Integer i) {
		return investigadores.get(i).capacidad();
	}
	public static Integer getDiasNecesarios(Integer j, Integer k) {
		return trabajos.get(j).diasRequeridos().get(k);
	}
	public static Integer getCalidadTrbj(Integer j) {
		return trabajos.get(j).calidad();
	}
	
	public static void ejercicio3_model() throws IOException {
		DatosEjercicio3.iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");

		investigadores = DatosEjercicio3.getInvestigadores();
		trabajos = DatosEjercicio3.getTrabajos();
		especialidades = DatosEjercicio3.getEspecialidades();
		
		//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
		AuxGrammar.generate(Ejercicio3PLE.class,"lsi_models/Ejercicio3.lsi","gurobi_models/Ejercicio3-3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio3-3.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		
	}
	
	public static void main(String[] args) throws IOException {	
		ejercicio3_model();
	}

}
