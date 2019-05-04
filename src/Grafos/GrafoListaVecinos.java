package Grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GrafoListaVecinos<T> extends Grafo<T>{
	public GrafoListaVecinos() {
		// TODO Auto-generated constructor stub
		
	}
	public int agregarVertice(T contenido) {
		
		int idAntiguo = NumeroVertices;
		VerticeVecinos<T> verticeNuevo = new VerticeVecinos<T>();
		if(vertices.put(NumeroVertices, verticeNuevo) == null) {
			NumeroVertices++;
			return idAntiguo;
		}
		return -1;	
	}
	public int eliminarVertice(int identificador) {
		if(vertices.remove(identificador) != null) 
			return identificador;
		else
			return -1;
	}
	public boolean agregarArista (int origen, int destino, int costo) {
		
		if(vertices.containsKey(origen) && vertices.containsKey(destino)) {
			Arista<T> aristaNueva = new Arista <T> (vertices.get(origen), vertices.get(destino), costo);
			((VerticeVecinos<T>) vertices.get(origen)).agregarVecino(aristaNueva);
			return true;
		}
		return false;
		
	}
	public boolean eliminarArista (int origen, int destino) {
		
		if(vertices.containsKey(origen) && vertices.containsKey(destino)) {
			return ((VerticeVecinos<T>) vertices.get(origen)).eliminarVecino(destino);
		}
		
		return false;
	}
	public List<Vertice<T>> obtenerVecinos (int identificador){
		
		ArrayList <Vertice<T>> listaSalida = new ArrayList<Vertice<T>>();
		for(Arista<T> a : ((VerticeVecinos<T>) vertices.get(identificador)).getVecinos()) {
			listaSalida.add(a.getDestino());
		}
		return listaSalida;
	}
	public List<Vertice<T>> obtenerVecinos (Vertice <T> vertice){
		
		ArrayList <Vertice<T>> listaSalida = new ArrayList<Vertice<T>>();
		
		for(Map.Entry<Integer, Vertice<T>> e : vertices.entrySet()) {
			for(Arista<T> a : ((VerticeVecinos<T>) vertices.get(vertice)).getVecinos()) {
				listaSalida.add(a.getDestino());
			}
		}
		return listaSalida;
	}
}