package _datos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.common.Files2;
import us.lsi.common.String2;


public class DatosEjercicio3 {
	
	public record Investigador (String nombre, Integer capacidad, Integer especialidad) {
//		INV1: capacidad=6; especialidad=0;
		public static Investigador create(String linea) {
			String[] v = linea.split(":");
			String[] v2 = v[1].split(";");
			return new Investigador(v[0], Integer.parseInt(v2[0].replace(" capacidad=", "")),
					Integer.parseInt(v2[1].replace(" especialidad=", "")));
		}
	}
	
	public static record Trabajo(String nombre, Integer calidad, List<Integer> diasRequeridos) {
		public static Trabajo create(String linea) {
//			T01 -> calidad=5; reparto=(0:6),(1:0),(2:0);
			String[] v = linea.split(" -> ");
			String s = v[1].replace("calidad=", "").replace(" reparto=(", "").replace(")", "").replace("(", "");
			
			String[] v2 = s.split(";");
			List<Integer> comp = new ArrayList<>();
			for (int i = 0; i < especialidades.size(); i++) {
				comp.add(0);
			}
			String[] v3 = v2[1].split(",");
			for (int i = 0; i < v3.length; i++) {
				String[] v4 = v3[i].split(":");
				int pos = Integer.parseInt(v4[0]);
				comp.set(pos, Integer.parseInt(v4[1]));
			}
			return new Trabajo(v[0], Integer.parseInt(v2[0].strip()), comp);
		}	
	}
	
	public static List<Investigador> investigadores;
	public static List<Trabajo> trabajos;
	public static List<Integer> especialidades;
	
	public static void iniDatos(String fichero) {
		
		List<String> lineas = Files2.linesFromFile(fichero);
		lineas.remove(0);
		
		Integer p1 = lineas.indexOf("// TRABAJOS");
		
		investigadores = lineas.subList(0, p1).stream().map(linea -> Investigador.create(linea)).collect(Collectors.toList());
		especialidades = investigadores.stream().map(Investigador::especialidad).distinct().collect(Collectors.toList());
		trabajos = lineas.subList(p1+1, lineas.size()).stream().map(linea -> Trabajo.create(linea)).collect(Collectors.toList());
		
		toConsole();
	}
	
	public static List<Investigador> getInvestigadores(){
		return investigadores;
	}
	public static List<Trabajo> getTrabajos(){
		return trabajos;
	}
	public static List<Integer> getEspecialidades(){
		return especialidades;
	}
	
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
	public static Integer getEspInv(Integer i) {
		return investigadores.get(i).especialidad();
	}
	public static Integer getDiasDispl(Integer i) {
		return investigadores.get(i).capacidad();
	}
	public static Integer getDiasNecesarios(Integer j, Integer k) {
		return trabajos.get(j).diasRequeridos().get(k);
	}
	
	public static List<Integer> getDiasTrab(Integer j) {
		return trabajos.get(j).diasRequeridos();
	}
	public static Integer getCalidadTrbj(Integer j) {
		return trabajos.get(j).calidad();
	}
	public static List<Integer> getEspsdelTrbj(Integer j) {
		return IntStream.range(0, getE()).filter(k->getDiasNecesarios(j, k)>0).boxed().collect(Collectors.toList());
	}

	private static void toConsole() {
		String2.toConsole("Investigadores: %s", investigadores);
		String2.toConsole(trabajos,"trabajos");
		String2.toConsole(especialidades,"especialidades");
		String2.toConsole(String2.linea());
	}
	public static void main(String[] args) {
		iniDatos("ficheros/Ejercicio3DatosEntrada3.txt");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
