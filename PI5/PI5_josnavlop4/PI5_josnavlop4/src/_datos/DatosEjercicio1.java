package _datos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DatosEjercicio1 {
	
	public record Tipo(String nombre, Integer kgdisp) {
//		C01: kgdisponibles=5;
		public static Tipo create(String linea) {
			String[] v = linea.split(":");
			String[] v2 = v[1].split(";");
			return new Tipo(v[0], Integer.parseInt(v2[0].replace(" kgdisponibles=", "")));
		}
	}
	
	public static record Variedad (String nombre, Integer beneficio, List<Double> comp) {
		public static Variedad create(String linea) {
//			P01 -> beneficio=20; comp=(C01:0.5),(C02:0.4),(C03:0.1);
			String[] v = linea.split(" -> ");
			String s = v[1].replace("beneficio=", "").replace(" comp=(", "").replace(")", "").replace("(", "");
			
			String[] v2 = s.split(";");
			List<Double> comp = new ArrayList<>();
			for (int i = 0; i < tipos.size(); i++) {
				comp.add(0.);
			}
			v2[1] = v2[1].replace("C", "");
			
			String[] v3 = v2[1].split(",");
			for (int i = 0; i < v3.length; i++) {
				String[] v4 = v3[i].split(":");
				int pos = Integer.parseInt(v4[0]) - 1;
				comp.set(pos, Double.parseDouble(v4[1]));
			}

			return new Variedad(v[0], Integer.parseInt(v2[0].strip()), comp);
		}	
	}
	
	//xi: variable entera, indica cuántos kilogramos de la variedad i serán fabricados, i en [0,m) 

	public static List<Tipo> tipos;
	public static List<Variedad> variedades;
	
	public static Integer getNumTipos() {
		return tipos.size();
	}
	public static Tipo getTipos(Integer j) {
		return tipos.get(j);
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
	public static Double getMinMax(Integer i, List<Double> remaining) {
		List<Double> maximo = new ArrayList<>();
	    for (int j = 0; j < remaining.size(); j++) {
	    	if(DatosEjercicio1.getKgXVariedad(i, j)!=0) {
	    		maximo.add(remaining.get(j)/DatosEjercicio1.getKgXVariedad(i, j));	
	    	}	
	    }
	    return maximo.stream().sorted().findFirst().get();
	}
	
	public static List<Tipo> getTipos(){
		return tipos;
	}
	public static List<Variedad> getVariedades(){
		return variedades;
	}
	
	public static void iniDatos(String fichero) {
		
		List<String> lineas = Files2.linesFromFile(fichero);
		lineas.remove(0);
		Integer p1 = lineas.indexOf("// VARIEDADES");
		
		tipos = lineas.subList(0, p1).stream().map(linea -> Tipo.create(linea)).collect(Collectors.toList());
		variedades = lineas.subList(p1+1, lineas.size()).stream().map(linea -> Variedad.create(linea)).collect(Collectors.toList());
		
		toConsole();
	}

	public static void toConsole() {
		String2.toConsole("Tipos: %s", tipos);
		String2.toConsole(variedades,"Variedades");
		String2.toConsole(String2.linea());
	}
	
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio1DatosEntrada1.txt");
	}
}
