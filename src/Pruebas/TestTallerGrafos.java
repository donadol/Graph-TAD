package Pruebas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Grafos.EstadoTigreBurroPaja;
import Grafos.Grafo;
import Grafos.GrafoTigreBurroPaja;
import Grafos.Vertice;

public class TestTallerGrafos {

	public TestTallerGrafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}
	
	private static <T> void probarPunto1() {
		TestGrafoListaVecinos<T> grafoListaVecinos = new TestGrafoListaVecinos<T>();
		
		System.out.println ("Punto 1: Clase abstracta Grafo con todos los métodos vistos en clase.  Al menos deben estar presentes: dfs, bfs, dijkstra, floydWarshall, bellmanFord, kruskal, prim.");
		
		System.out.println ("DFS: ");
		
		System.out.println ("BFS: ");
		
		System.out.println ("Dijkstra: ");

		System.out.println ("Floyd Warshall: ");
		
		System.out.println ("Bellman Ford: ");
		
		System.out.println ("Kruskal: ");
		
		System.out.println ("Prim: ");
		
		
	}
	
	
	//Esta función permite imprimir el camino de dijkstra de dos puntos
	private static void construirCaminoDijkstra(GrafoTigreBurroPaja grafoTigreBurroPaja, Map<Integer, Vertice<EstadoTigreBurroPaja>> previo, int identificador) {
		
		
		boolean caminoTerminado = false;
		List<EstadoTigreBurroPaja> caminoAlReves = new ArrayList<EstadoTigreBurroPaja>();
		caminoAlReves.add(grafoTigreBurroPaja.obtenerVertice(identificador).getContenido());
			
		
		while (!caminoTerminado) {
			if (previo.get(identificador)!= null) {
				caminoAlReves.add(previo.get(identificador).getContenido());
				identificador = previo.get(identificador).getIdentificador();
			}
			else {
				caminoTerminado = true;
			}
				
		}
		
		Collections.reverse(caminoAlReves);
		
		for (EstadoTigreBurroPaja estado: caminoAlReves) {
			System.out.println(estado);
		}
	}
	
private static <T> void construirCaminoDijkstra(Grafo <T> grafo, Map<Integer, Vertice<T>> previo, int identificador, String etiqueta) {
	
		
		System.out.println(etiqueta+":\n");
		
		
		boolean caminoTerminado = false;
		List<T> caminoAlReves = new ArrayList<T>();
		caminoAlReves.add(grafo.obtenerVertice(identificador).getContenido());
			
		
		while (!caminoTerminado) {
			if (previo.get(identificador)!= null) {
				caminoAlReves.add(previo.get(identificador).getContenido());
				identificador = previo.get(identificador).getIdentificador();
			}
			else {
				caminoTerminado = true;
			}
				
		}
		
		Collections.reverse(caminoAlReves);
		
		for (T contenido: caminoAlReves) {
			System.out.println(contenido);
		}
	}
	
	

}
