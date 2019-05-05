package Grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class GrafoAristas<T> extends Grafo<T>{

	private List<Arista<T>> aristas;
	
	public GrafoAristas() 
	{
		super();
		aristas = new ArrayList <Arista<T>>();
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

}
