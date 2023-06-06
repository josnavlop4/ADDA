package ejercicio4;

import java.util.List;

import _datos.DatosEjercicio4;
import _soluciones.SolucionEjercicio4;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class SeqNormalDataEjercicio4AG implements SeqNormalData<SolucionEjercicio4>{
	
	public SeqNormalDataEjercicio4AG(String linea) {
		DatosEjercicio4.iniDatos(linea);
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
	    Double goal = 0.0;
	    Double error = 0.0;
	    Double kms = 0.0;
	    
	    for (int i = 0; i < value.size() - 1; i++) {
	        if (DatosEjercicio4.existeArista(value.get(i), value.get(i + 1))) {
	            kms += DatosEjercicio4.getKm(value.get(i), value.get(i + 1));
	            goal += DatosEjercicio4.getBeneficio(value.get(i+1))-kms;
	        } else {
	            error++;
	        }
	    }
	    // Añadir la distancia del ultimo vertice al almacén y el beneficio obtenido en el almacén
	    if (DatosEjercicio4.existeArista(value.get(value.size() - 1), 0)) {
	        kms += DatosEjercicio4.getKm(value.get(value.size() - 1), 0);
	    } else {
	        error += 10000; // Duplicar la penalización si no se puede llegar al almacén desde el último vértice
	    }
	    // error si el camino no comienza o termina en el almacén
	    if (value.get(0) != 0) {
	        error += 100000;
	    }
	    //le añade el beneficio del ultimo
	    goal-=kms;
	    return goal - 10000 * error;
	}

	@Override
	public SolucionEjercicio4 solucion(List<Integer> value) {
	    return SolucionEjercicio4.create(value);
	}
	
	@Override
	public Integer itemsNumber() {
		return DatosEjercicio4.getN();
	}


}
