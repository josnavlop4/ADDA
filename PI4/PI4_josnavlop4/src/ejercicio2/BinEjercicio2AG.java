package ejercicio2;

import java.util.List;
import java.util.Set;

import _datos.DatosEjercicio2;
import _soluciones.SolucionEjercicio2;
import us.lsi.ag.BinaryData;
import us.lsi.common.Set2;

public class BinEjercicio2AG implements BinaryData<SolucionEjercicio2>{
	
	public BinEjercicio2AG (String linea) {
		DatosEjercicio2.iniDatos(linea);
	}
	@Override
	public Integer size() {
		return DatosEjercicio2.getN();
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = 0.0;
		double error = 0.0;
		Set<Integer> t = Set2.empty();
		Set<Integer> c = Set2.empty();
		
		for(int i=0;i<size();i++) {
			if(ls.get(i)>0) {
				goal+=DatosEjercicio2.getCoste(i);
				t.addAll(DatosEjercicio2.getTematicaXCurso(i));
				c.add(DatosEjercicio2.getCentroXCurso(i));
			}
		}
		
		error+= DatosEjercicio2.getM()>t.size()?DatosEjercicio2.getM()-t.size():0;
		error+= c.size()>DatosEjercicio2.getMaxC()?c.size()-DatosEjercicio2.getMaxC():0;
		
		return -goal - 10000*error ;
		
	}

	@Override
	public SolucionEjercicio2 solucion(List<Integer> ls) {
		return SolucionEjercicio2.create(ls);
	}

}
