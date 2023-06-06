package ejercicio3;

import java.util.List;

import _datos.DatosEjercicio3;
import _soluciones.SolucionEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class InRangeEjercicio3AG implements ValuesInRangeData<Integer, SolucionEjercicio3> {
	
	public InRangeEjercicio3AG (String linea) {
		DatosEjercicio3.iniDatos(linea);
	}
	
	@Override
	public Integer size() {
		return DatosEjercicio3.getN()*DatosEjercicio3.getM();
	}

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		
		int n = DatosEjercicio3.getN();
		int m = DatosEjercicio3.getM();
		int e = DatosEjercicio3.getE();
		Double goal = 0.,error=0.;
		int[] diasXInvestigador = new int[n];
		
		for(int i=0; i<n;i++) {
			diasXInvestigador[i]=0;
		}
		for(int j = 0; j<m ;j++) {
			Boolean realizado = true;
			for(int k = 0; k<e ;k++) {
				int diasInvertEspecialidad = 0;
				for(int i = 0; i<n ;i++) {
					int diasInvertidos = value.get((j*n)+i);
					if(DatosEjercicio3.isEspXTrabajador(i, k)==1) {
						diasInvertEspecialidad+=diasInvertidos;
					}
				}
				if(diasInvertEspecialidad!=DatosEjercicio3.getDiasNecesarios(j,k)) {
					realizado=false;
					break;
				}
			}
			if(realizado) {
				goal+=DatosEjercicio3.getCalidadTrbj(j);
			}
		}
		for(int j=0;j<m;j++) {
			for(int i=0; i<n;i++) {
				int diasInvertidos = value.get((j*n)+i);
				diasXInvestigador[i]+=diasInvertidos;
				if(diasXInvestigador[i]>DatosEjercicio3.getDiasDispl(i)) {
					error+=DatosEjercicio3.getCalidadTrbj(j);
//					break;
				}
			}
		}
		
		return goal-10*error;
	}
	
	@Override
	public SolucionEjercicio3 solucion(List<Integer> value) {
		return SolucionEjercicio3.of(value);
	}

	@Override
	public Integer max(Integer i) {
		return DatosEjercicio3.getDiasDispl(i%DatosEjercicio3.getN())+1;
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

}
