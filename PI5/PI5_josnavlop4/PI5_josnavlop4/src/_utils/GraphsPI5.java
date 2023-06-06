package _utils;

import java.util.function.Predicate;

import ejercicios.ejercicio1.Ejercicio1Edge;
import ejercicios.ejercicio1.Ejercicio1Heuristic;
import ejercicios.ejercicio1.Ejercicio1Vertex;
import ejercicios.ejercicio2.Ejercicio2Edge;
import ejercicios.ejercicio2.Ejercicio2Heuristic;
import ejercicios.ejercicio2.Ejercicio2Vertex;
import ejercicios.ejercicio3.Ejercicio3Edge;
import ejercicios.ejercicio3.Ejercicio3Heuristic;
import ejercicios.ejercicio3.Ejercicio3Vertex;
import ejercicios.ejercicio4.Ejercicio4Edge;
import ejercicios.ejercicio4.Ejercicio4Heuristic;
import ejercicios.ejercicio4.Ejercicio4Vertex;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraphBuilder;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

// Clase Factoria para crear los constructores de los grafos de los ejemplos y ejercicios
public class GraphsPI5 {
	
	//Ejercicio 1
	public static EGraphBuilder<Ejercicio1Vertex, Ejercicio1Edge>
		multisetBuilder(Ejercicio1Vertex v_inicial, Predicate<Ejercicio1Vertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
					// TODO Defina en el tipo vertice un m. static / Predicate para los vertices solucion
				.goalHasSolution(Ejercicio1Vertex.goalHasSolution())
				.heuristic(Ejercicio1Heuristic::heuristic);
	}
	
	//Ejercicio 2
	public static EGraphBuilder<Ejercicio2Vertex, Ejercicio2Edge>
		multisetBuilder(Ejercicio2Vertex v_inicial, Predicate<Ejercicio2Vertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Min)
					// TODO Defina en el tipo vertice un m. static / Predicate para los vertices solucion
				.goalHasSolution(Ejercicio2Vertex.goalHasSolution())
				.heuristic(Ejercicio2Heuristic::heuristic);
	}
	
	//Ejercicio 3
	public static EGraphBuilder<Ejercicio3Vertex, Ejercicio3Edge>
		multisetBuilder(Ejercicio3Vertex v_inicial, Predicate<Ejercicio3Vertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
					// TODO Defina en el tipo vertice un m. static / Predicate para los vertices solucion
				.goalHasSolution(Ejercicio3Vertex.goalHasSolution())
				.heuristic(Ejercicio3Heuristic::heuristic);
	}
	
	//Ejercicio 4
	public static EGraphBuilder<Ejercicio4Vertex, Ejercicio4Edge>
		multisetBuilder(Ejercicio4Vertex v_inicial, Predicate<Ejercicio4Vertex> es_terminal) { 
		return EGraph.virtual(v_inicial, es_terminal, PathType.Sum, Type.Max)
			// TODO Defina en el tipo vertice un m. static / Predicate para los vertices solucion
			.goalHasSolution(Ejercicio4Vertex.goalHasSolution())
			.heuristic(Ejercicio4Heuristic::heuristic);
	}
	
}
