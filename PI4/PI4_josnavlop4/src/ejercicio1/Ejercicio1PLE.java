package ejercicio1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Tipo;
import _datos.DatosEjercicio1.Variedad;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ejercicio1PLE {
	
	private static List<Tipo> tipos;
	private static List<Variedad> variedades;
	
	public static Integer getNumTipos() {
		return tipos.size();
	}
	public static Integer getNumVariedades() {
		return variedades.size();
	}
	public static Integer getKgdisp(Integer j) {
		return tipos.get(j).kgdisp();
	}
	public static Integer getBeneficio(Integer i) {
		return variedades.get(i).beneficio();
	}
	public static Double getKgXVariedad(Integer i, Integer j) {
		return variedades.get(i).comp().get(j);
	}
	public static void ejercicio1_model() throws IOException {
		DatosEjercicio1.iniDatos("ficheros/Ejercicio1DatosEntrada3.txt");

		tipos = DatosEjercicio1.getTipos();
		variedades = DatosEjercicio1.getVariedades();
		
		//si cambia el fichero de datos de entrada, cambiar tambien el nº del .lp para no sobreescribirlo
		AuxGrammar.generate(Ejercicio1PLE.class,"lsi_models/Ejercicio1.lsi","gurobi_models/Ejercicio1-3.lp");
		GurobiSolution solution = GurobiLp.gurobi("gurobi_models/Ejercicio1-3.lp");
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(solution.toString((s,d)->d>0.));
		
	}
	
	public static void main(String[] args) throws IOException {	
		ejercicio1_model();
	}

}
