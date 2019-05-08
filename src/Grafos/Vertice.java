package Grafos;

import java.util.LinkedList;
import java.util.List;


public class Vertice <T>{
	private T contenido;
	private boolean marcado;
	private int identificador;
	//Representa la distancia del vertice al vertice de destino en los algoritmos de Dijkstra y Prim
	private int distancia;
	//Representa la lista de aristas que tiene este vértice
	private List<Arista <T>> vecinos;
	
	private List<Vertice<T>> vertices;
	
	 private List<Vertice> menorDistancia = new LinkedList<>();
	
	public Vertice(){}
	public Vertice(T contenido, int id){
		this.contenido=contenido;
		this.marcado=false;
		this.identificador=id;
	}
	public T getContenido() {
		return contenido;
	}
	public void setContenido(T contenido) {
		this.contenido = contenido;
	}
	public boolean isMarcado() {
		return marcado;
	}
	public void setMarcado(boolean marcado) {
		this.marcado = marcado;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public List<Arista<T>> getVecinos() {
		// TODO Auto-generated method stub
		return vecinos;
	}
	
	public List<Vertice> getMenorDistancia() {
		return menorDistancia;
	}

	public void setMenorDistancia(List<Vertice> menorDistancia) {
		this.menorDistancia = menorDistancia;
	}
	
	public List<Vertice<T>> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice<T>> vertices) {
		this.vertices = vertices;
	}
	
}


