package _soluciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _datos.DatosEjercicio1;
import _datos.DatosEjercicio1.Variedad;

public class SolucionEjercicio1 {
	
	private Map<Variedad,Integer> d;
	private Double beneficio;
	
	public static SolucionEjercicio1 of(List<Integer> value) {
		return new SolucionEjercicio1(value);
	}
	
	private SolucionEjercicio1(List<Integer> value) {
		System.out.println(value);
		d = new HashMap<>();
		beneficio = 0.0;
		for (int i=0;i<value.size();i++) {
			Variedad v = DatosEjercicio1.variedades.get(i);
			Integer kgVariedad = value.get(i);
			if(kgVariedad!=0) {
				d.put(v, kgVariedad);
				beneficio+=kgVariedad*v.beneficio();
			}
		}
	}

	@Override
	public String toString() {
		String cad = "Kg que deben producirse para maximizar beneficios: \n";
		for(Variedad v:d.keySet()) {
			cad+=v.nombre()+": "+d.get(v)+" kgs\n";
		}
		cad+="Beneficio: "+beneficio;
		return cad;
	}
	
}
