package _datos;

import java.util.Set;

import org.jgrapht.Graph;

import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class DatosEjercicio4 {
	
	public static Graph<Cliente,Conexion> g;
	
	public static void iniDatos(String file) {
		g = GraphsReader.newGraph("ficheros/" + file + ".txt",
					Cliente::ofFormat, 
					Conexion::ofFormat, 
					Graphs2::simpleWeightedGraph,
					Conexion::kms);
	
		System.out.println("\nArchivo " + file + ".txt \n" + "Datos de entrada: " + g);
	}
	//entero, número de vertices
	public static Integer getN() {
		return g.vertexSet().size();
	}
	//vertice i 
	public static Cliente getV(Integer i) {
		return g.vertexSet().stream().filter(n->n.id()==i).findFirst().get();
	}
	//aristas del grafo
	public static Set<Conexion> getE(){
		return g.edgeSet();
	}
	//vertice origen, a en [0,n)
	
	//double, peso de la arista (i,j), i y j en [0,n)
	public static Double getKm(Integer i, Integer j) {
		return g.getEdgeWeight(g.getEdge(getV(i),getV(j)));
	}
	//double, beneficio del cliente ubicado en el vertice i, i en [0,n)
	public static Double getBeneficio(Integer i) {
		return getV(i).beneficio();
	}
	//Comprobar si existe la conexion entre dos aristas para la restriccion
	public static Boolean existeArista (Integer i,Integer j) {
		return g.containsEdge(g.getEdge(getV(i), getV(j)));
	}
	
	public static void main(String[] args) {
		iniDatos("./Ejercicio4DatosEntrada1");
		System.out.println(getKm(0,3));
		System.out.println(getBeneficio(0));
	}
	

}
