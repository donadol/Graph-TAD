package GrafoListaAristas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import GrafoListaVecinos.VerticeVecinos;
import Grafos.Arista;
import Grafos.Grafo;
import Grafos.LimiteException;
import Grafos.Vertice;

public abstract class GrafoAristas<T> extends Grafo<T>{

	public GrafoAristas() {
		super();
	}


	public List<Arista<T>> obtenerAristas (){
		ArrayList<Arista<T>> listaSalida = new ArrayList<Arista<T>>();
		for(Entry<Integer, Vertice<T>> entry : vertices.entrySet()) {
			for(Arista<T> arista : entry.getValue().getVecinos()) {
				listaSalida.add(arista);
			}
		}
		return listaSalida;
	}

	public int obtenerCostoArista (int origen, int destino) throws LimiteException{
		for(Arista<T> a : obtenerAristas()) {
			if(a.getOrigen().getIdentificador() == origen && a.getDestino().getIdentificador() == destino) {
				return a.getCosto();
			}
		}
		return INF;
	}

	public int obtenerCostoArista (Vertice <T> origen, Vertice <T> destino) throws LimiteException{
		for(Arista<T> a : obtenerAristas()) {
			if(origen.getIdentificador() ==a.getOrigen().getIdentificador()  && destino.getIdentificador() ==a.getDestino().getIdentificador()) {
				return a.getCosto();
			}
		}
		return INF;
	}

	public int agregarVertice(T contenido) {
		int idAntiguo = NumeroVertices;
		VerticeVecinos<T> verticeNuevo = new VerticeVecinos<T>();
		verticeNuevo.setContenido(contenido);
		verticeNuevo.setIdentificador(NumeroVertices);
		vertices.put(NumeroVertices, verticeNuevo);
		if(vertices.containsKey(NumeroVertices)) {
			NumeroVertices++;
			return idAntiguo;
		}
		return -1;	
	}
	
	public int eliminarVertice(int identificador) {
		int valorRetorno = -1;
		if(vertices.containsKey(identificador)) {
			valorRetorno = identificador;
			vertices.remove(identificador);
			for(Map.Entry<Integer,Vertice<T>> entry : vertices.entrySet()) {
				((VerticeVecinos<T>) entry.getValue()).eliminarVecino(identificador);
			}
		}
		return valorRetorno;
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
			if(((VerticeVecinos<T>) vertices.get(origen)).existeVecino(destino)) {
				return ((VerticeVecinos<T>) vertices.get(origen)).eliminarVecino(destino);
			}
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
		for(Entry<Integer, Vertice<T>> e : vertices.entrySet()) {
			if(e.getValue().getIdentificador() == vertice.getIdentificador()) {
				for(Arista<T> a : ((VerticeVecinos<T>) e.getValue()).getVecinos()) {
					listaSalida.add(a.getDestino());
				}
				break;
			}
		}
		return listaSalida;
	}

	public Arista<T> obtenerArista (int origen, int destino){
		for(Entry<Integer, Vertice<T>> entry : vertices.entrySet()) {
			if(entry.getValue().getIdentificador() == origen) {
				for(Arista<T> arista : ((VerticeVecinos<T>) entry.getValue()).getVecinos()) {
					if(arista.getDestino().getIdentificador() == destino) {
						return arista;
					}
				}
				break;
			}
		}
		return null;
	}
	
	public Arista<T> obtenerArista (Vertice<T> origen, Vertice<T> destino){
		return obtenerArista(origen.getIdentificador(), destino.getIdentificador() );
	}

	public List<Arista<T>> obtenerAristasVertice (int identificador){
		ArrayList<Arista<T>> listaSalida = new ArrayList<Arista<T>>();
		for(Entry<Integer, Vertice<T>> entry : vertices.entrySet()) {
			if(entry.getValue().getIdentificador() == identificador) {
				for(Arista<T> arista : ((VerticeVecinos<T>) entry.getValue()).getVecinos()) {
					listaSalida.add(arista);
				}
				break;
			}
		}
		return listaSalida;
	}
	
	public List<Arista<T>> obtenerAristasVertice (Vertice<T> vertice){
		return obtenerAristasVertice(vertice.getIdentificador());
	}
}
