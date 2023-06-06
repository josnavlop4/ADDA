package ejercicio1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import _datos.DatosEjercicio1;
import _soluciones.SolucionEjercicio1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeEjercicio1AG implements ValuesInRangeData<Integer, SolucionEjercicio1>{
	
	public InRangeEjercicio1AG (String linea) {
		DatosEjercicio1.iniDatos(linea);
	}

	@Override
	public Integer size() {
		return DatosEjercicio1.getNumVariedades();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		Map<Integer,Double> res = new HashMap<>();
		double goal = 0,error = 0;
		
		for(int i=0;i<size();i++) {
			if(value.get(i)>0) {
				goal+= value.get(i)*DatosEjercicio1.getBeneficio(i);
			}
		}
		for(int j=0;j<DatosEjercicio1.getNumTipos();j++) {
			for(int i=0;i<size();i++) {	
				Integer clave = j;
				Double valor = value.get(i)*DatosEjercicio1.getKgXVariedad(i, j);
				
				if (res.containsKey(clave)) {
					Double ac = res.get(clave)+ valor;
					res.put(clave,ac);
				}else {
					res.put(clave, valor);
				}
			}
		}
		for(int j=0;j<DatosEjercicio1.getNumTipos();j++) {
			error+=res.get(j)>DatosEjercicio1.getKgdisp(j)? res.get(j) - DatosEjercicio1.getKgdisp(j):0;
		}
		return goal - 10000*error;
	}

	@Override
	public SolucionEjercicio1 solucion(List<Integer> value) {
		return SolucionEjercicio1.of(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicio1.getMax(i)+1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}
}
