package Grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Grafo <T>{
	
	
	
	private int identificadorVertice;
	Map <Integer, Vertice<T>> vertices;
	
	
	public Grafo() {
		this.identificadorVertice = 0;
		vertices = new HashMap <Integer, Vertice<T>> ();
		
	}
	

	public abstract int agregarVertice(T contenido);
	public abstract int eliminarVertice(int identificador);
	
	public abstract void agregarArista (int origen, int destino, int costo);
	public abstract void eliminarArista (int origen, int destino, int costo);
	
	public abstract List<Vertice<T>> obtenerVecinos (int identificador);
	public abstract List<Vertice<T>> obtenerVecinos (Vertice <T> vertice);
		
	
	
	public void BFS (int identificador) {
		
	}
	
	
	
	public void reiniciarMarcas () {
		
	}
	
	
	
	
	
}
