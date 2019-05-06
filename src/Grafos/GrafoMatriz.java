package Grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public abstract class GrafoMatriz<T> extends Grafo<T>{

	private  int cantArcos;
	private  ArrayList< ArrayList< Integer>> matrizDeAdyacencia;
	private  ArrayList< Boolean> nodosVisitados;
	private  ArrayList< Integer> identificadores;
	
	public GrafoMatriz() {
		super();
		identificadores = new ArrayList<Integer>();
		this.cantArcos = 0;
		this.matrizDeAdyacencia = new ArrayList<ArrayList< Integer>>();
		this.nodosVisitados = new ArrayList<Boolean>();
	}
	
	public int agregarVertice(T contenido) {
		
		
		ArrayList<Integer> verticeAux = new ArrayList< Integer>();
		for(int i = 0; i < matrizDeAdyacencia.size() + 1; i++)
			verticeAux.add(INF);
		verticeAux.set(verticeAux.size() - 1,  0);
		matrizDeAdyacencia.add(verticeAux);
		
		for(int i = 0; i < matrizDeAdyacencia.size(); i++)
			for(int j = 0; j < matrizDeAdyacencia.get(matrizDeAdyacencia.size() - 1).size() -  matrizDeAdyacencia.get(i).size(); j++)
				matrizDeAdyacencia.get(i).add(INF);
		
		nodosVisitados.add(false);
		cantArcos++;
		Vertice <T> verticeNuevo = new Vertice<T>(contenido, NumeroVertices);
		identificadores.add(NumeroVertices);
		vertices.put(NumeroVertices,verticeNuevo);
		NumeroVertices++;
		return NumeroVertices - 1;
		
	}
	public int eliminarVertice(int identificador) {
		
		
		if(existeVertice(identificador)) {
			
			int posicion = existeVertice2(identificador);
			vertices.remove(identificador);
			matrizDeAdyacencia.remove(posicion);
			for(ArrayList< Integer> n : matrizDeAdyacencia)
				n.remove(posicion);
			cantArcos--;
			identificadores.remove(posicion);
			nodosVisitados.remove(posicion);
			return identificador;
		}else
			return -1;
		
	}

	public boolean agregarArista (int origen, int destino, int costo) {
		
		
		if(existeVertice(origen) && existeVertice(destino)){
			
			int posOrigen = existeVertice2(origen);
			int posDestino = existeVertice2(destino);
			matrizDeAdyacencia.get(posOrigen).set(posDestino,  costo);
			cantArcos++;
			return true;
		}else {
			return false;
		}
		
	}
	public boolean eliminarArista (int origen, int destino) {
		
		int posOrigen = existeVertice2(origen);
		int posDestino = existeVertice2(destino);
		if(matrizDeAdyacencia.size() > 0) {
			if(existeVertice(posOrigen) && existeVertice(posDestino)) {
				if(posOrigen != posDestino) {
					matrizDeAdyacencia.get(posOrigen).set(posDestino, INF);
					cantArcos--;
				}else { 
					matrizDeAdyacencia.get(posOrigen).set(posDestino, 0);
				}
				return true;
			}else {
				return false;
			}
		}
		return false;
		
	}

	public List<Vertice<T>> obtenerVecinos (int identificador){
		
		ArrayList <Vertice<T>> listaSalida = new ArrayList<Vertice<T>>();
		if(existeVertice(identificador)) {
			int i = 0;
			for(Integer l : matrizDeAdyacencia.get(existeVertice2(identificador))) {
					if(l != INF && i != existeVertice2(identificador)) {
						listaSalida.add(vertices.get(i));
					}
					i++;
			}
			return listaSalida;
		}
		return null;
		
	}
	public List<Vertice<T>> obtenerVecinos (Vertice <T> vertice){
		
		int identificador = vertice.getIdentificador();
		ArrayList <Vertice<T>> listaSalida = new ArrayList<Vertice<T>>();
		if(existeVertice(identificador)) {
			int i = 0;
			for(Integer l : matrizDeAdyacencia.get(existeVertice2(identificador))) {
					if(l != INF && i != existeVertice2(identificador)) {
						listaSalida.add(vertices.get(i));
					}
					i++;
			}
			return listaSalida;
		}
		return null;
	}

	public List<Arista<T>> obtenerAristas (){
		
		ArrayList<Arista<T>> listaSalida = new ArrayList<Arista<T>>();
		int i = 0;
		int j = 0;
		for(ArrayList<Integer> l : matrizDeAdyacencia) {
			j = 0;
			for(Integer  e : l) {
				if(e != INF && i != j) {
					Arista<T> aristaAux = new Arista<T>();
					aristaAux.setOrigen(vertices.get(identificadores.get(i)));
					aristaAux.setDestino(vertices.get(identificadores.get(j)));
					aristaAux.setCosto(e);
					listaSalida.add(aristaAux);
					
				}
				j++;
			}
			i++;
		}
		
		return listaSalida;
	}

	public int obtenerCostoArista (int origen, int destino) {
		int posOrigen = existeVertice2(origen);
		int posDestino = existeVertice2(destino);
		if(existeVertice(posOrigen) && existeVertice(posDestino)) {
			return matrizDeAdyacencia.get(posOrigen).get(posDestino);
		}
		
		return INF;
	}
	public int obtenerCostoArista (Vertice <T> origen, Vertice <T> destino) {
		int posOrigen = existeVertice2(origen.getIdentificador());
		int posDestino = existeVertice2(destino.getIdentificador());
		if(existeVertice(posOrigen) && existeVertice(posDestino)) {
			return matrizDeAdyacencia.get(posOrigen).get(posDestino);
		}
		
		return INF;
	}
	
	public boolean existeVertice(int idVertice) {
		
		for(Integer i : identificadores) {
			if(i == idVertice)
				return true;
		}
		return false;
	}
	
	public int existeVertice2(int idVertice) {
		
		int j = 0;
		for(Integer i : identificadores) {
			if(i == idVertice)
				return j;
			j++;
		}
		return -1;
	}
	
	public  void imprimirGrafo() {
		int i = 0;
		for(ArrayList< Integer> n : matrizDeAdyacencia) {
			System.out.print( "id " + identificadores.get(i)+ ":   ");
			for(int m : n) {
					System.out.print(m + "             ");
				
			}
			System.out.print("\n");
			System.out.print("\n");
			System.out.print("\n");
			i++;
		}
	}
	
	public Arista<T> obtenerArista (int origen, int destino){
		
		ArrayList<Arista<T>> listaAux = new ArrayList<Arista<T>>();
		listaAux = (ArrayList<Arista<T>>) obtenerAristas();
		for(Arista<T> a : listaAux) {
			if(a.getOrigen().getIdentificador() == origen && a.getDestino().getIdentificador() == destino) {
				return a;
			}
		}
		return null;

	}
	public Arista<T> obtenerArista (Vertice<T> origen, Vertice<T> destino){
		
		return obtenerArista(origen.getIdentificador(), destino.getIdentificador() );
	}
	
	
	public List<Arista<T>> obtenerAristasVertice (int identificador){
		
		ArrayList<Arista<T>> listaAux = new ArrayList<Arista<T>>();
		ArrayList<Arista<T>> listaSalida = new ArrayList<Arista<T>>();
		listaAux = (ArrayList<Arista<T>>) obtenerAristas();
		for(Arista<T> a : listaAux) {
			if(a.getOrigen().getIdentificador() == identificador) {
				listaSalida.add(a);
			}
		}
		return listaSalida;
	}
	public List<Arista<T>> obtenerAristasVertice (Vertice<T> vertice){
		
		return obtenerAristasVertice(vertice.getIdentificador());
	}

}
