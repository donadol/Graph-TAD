package Grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class Grafo <T>{
	
	protected static int INF = Integer.MAX_VALUE;
	
	
	
	protected int identificadorVertice;
	Map <Integer, Vertice<T>> vertices;
	
	
	
	
	public Grafo() {
		this.identificadorVertice = 0;
		vertices = new HashMap <Integer, Vertice<T>> ();
		
	}
	

	public abstract int agregarVertice(T contenido);
	public abstract int eliminarVertice(int identificador);
	
	public abstract boolean agregarArista (int origen, int destino, int costo);
	public abstract boolean eliminarArista (int origen, int destino);
	
	public abstract List<Vertice<T>> obtenerVecinos (int identificador);
	public abstract List<Vertice<T>> obtenerVecinos (Vertice <T> vertice);
	
	
	
	//Funci�n que retorna el costo de una artista dado el origen y destino
	public abstract int obtenerCostoArista (int origen, int destino);
	public abstract int obtenerCostoArista (Vertice <T> origen, Vertice <T> destino);
	
	
	
	//Dijkstra: Permite encontrar el camino m�s corto de un vertice a todos los dem�s vertices
	//Retorna: Un mapa donde cada con cada identificador del vertice, se puede obtener el vertice previo que sigue el camino m�s corto
	
	public Map<Integer, Vertice<T>> dijkstra (int inicio){

		Vertice<T> v;
		
		//Reinicia los valores de distancia de cada uno de los vertices
		reiniciarDistancias();
		reiniciarMarcas();
		
		
		//Crea el mapa resultante, el inicio tiene como previo un valor nulo
		Map <Integer, Vertice<T>> previo = new HashMap <Integer, Vertice<T>>();
		previo.put(inicio, null);
		
		
		//El vertice de inicio tiene una distancia 0 (porque es �l mismo)
		vertices.get(inicio).setDistancia(0);		
		vertices.get(inicio).setMarcado(true);	
		
		PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<Vertice<T>>(vertices.size(), new ComparadorDijkstra<T> ());
		colaPrioridad.add(vertices.get(inicio));
		
		
		while (!colaPrioridad.isEmpty()) {
			v = colaPrioridad.remove();
			v.setMarcado(true);
			for (Vertice<T> u: obtenerVecinos(v)) {
				if (!u.isMarcado()) {
					if (u.getDistancia() > v.getDistancia() + obtenerCostoArista(v, u)) {
						u.setDistancia(v.getDistancia() + obtenerCostoArista(v, u));
						
						
						
						previo.put(u.getIdentificador(), v);
						colaPrioridad.offer(u);
					}
				}
			}
		}	
		
		return previo;
		
	}
	
	
	//es necesario meter todos los vertices!
	//se inicia desde cualquier vertice
	
	public Map<Integer, Vertice<T>> prim(int inicio){
		Vertice<T> v;
		
		//Reinicia los valores de distancia de cada uno de los vertices
		reiniciarDistancias();
		reiniciarMarcas();
		
		
		//Crea el mapa resultante, el inicio tiene como previo un valor nulo
		Map <Integer, Vertice<T>> previo = new HashMap <Integer, Vertice<T>>();
		previo.put(inicio, null);
		
		
		//El vertice de inicio tiene una distancia 0 (porque es �l mismo)
		vertices.get(inicio).setDistancia(0);		
		vertices.get(inicio).setMarcado(true);	
		
		PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<Vertice<T>>(vertices.size(), new ComparadorDijkstra<T> ());
		colaPrioridad.add(vertices.get(inicio));
		
		
		while (!colaPrioridad.isEmpty()) {
			v = colaPrioridad.remove();
			v.setMarcado(true);
			for (Vertice<T> u: obtenerVecinos(v)) {
				if (!u.isMarcado()) {
					if (u.getDistancia() > obtenerCostoArista(v, u)) {
						u.setDistancia(obtenerCostoArista(v, u));		
						previo.put(u.getIdentificador(), v);
						colaPrioridad.offer(u);
					}
				}
			}
		}	
		
		return previo;
	}
	
	
	
	
		
	
	
	public void BFS (int identificador) {
		
	}
	
	
	
	public void reiniciarMarcas () {
		for (Vertice<T> vertice: vertices.values()) {
			vertice.setMarcado(false);
		}
	}
	
	
	//Para los algoritmos de Dijkstra y Prim, coloca las distancias de cada uno de los vertices en infinito.
	public void reiniciarDistancias() {
		for (Vertice<T> vertice: vertices.values()) {
			vertice.setDistancia(INF);
		}
	}
	
	
	
	
	
	
}
