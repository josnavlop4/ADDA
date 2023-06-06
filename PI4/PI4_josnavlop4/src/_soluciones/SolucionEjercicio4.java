package _soluciones;

import java.util.ArrayList;
import java.util.List;

import _datos.Cliente;
import _datos.DatosEjercicio4;

public class SolucionEjercicio4 {

	public static SolucionEjercicio4 create(List<Integer> ls) {
		return new SolucionEjercicio4 (ls);
	}
	
	private Double kms;
    private Double beneficio;
    private List<Cliente> clientes;
	
	public SolucionEjercicio4(List<Integer> ls) {
		System.out.println(ls);
		
	    kms = 0.0;
	    beneficio = 0.0;
	    clientes = new ArrayList<>();
	    
	    for (int i = 0; i<ls.size()-1;i++) {
	    	clientes.add(DatosEjercicio4.getV(ls.get(i)));
			if(DatosEjercicio4.existeArista(ls.get(i), ls.get(i+1))) {
				kms+=DatosEjercicio4.getKm(ls.get(i), ls.get(i+1));
				beneficio+=DatosEjercicio4.getBeneficio(ls.get(i+1))-kms;
			}	
	    }
	    if (DatosEjercicio4.existeArista(ls.get(ls.size()-1), 0)) {
			kms+=DatosEjercicio4.getKm(ls.get(ls.size()-1), 0);
		}
	    
	    clientes.add(DatosEjercicio4.getV(0));
	    beneficio -= kms;
	}

	@Override
	public String toString() {
		return "SolucionEjercicio4 [kms=" + kms + ", beneficio=" + beneficio + ", clientes=" + clientes + "]";
	}

}
