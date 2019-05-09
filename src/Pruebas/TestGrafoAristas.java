package Pruebas;

import java.util.List;

import Grafos.Arista;
import Grafos.GrafoAristas;
import Grafos.Vertice;

public class TestGrafoAristas <T> extends GrafoAristas<T>{

	public TestGrafoAristas() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int agregarVertice(T contenido) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarVertice(int identificador) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean agregarArista(int origen, int destino, int costo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarArista(int origen, int destino) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Vertice<T>> obtenerVecinos(int identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vertice<T>> obtenerVecinos(Vertice<T> vertice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arista<T> obtenerArista(int origen, int destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arista<T> obtenerArista(Vertice<T> origen, Vertice<T> destino) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Arista<T>> obtenerAristasVertice(int identificador) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Arista<T>> obtenerAristasVertice(Vertice<T> vertice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void imprimirGrafo() {
		// TODO Auto-generated method stub
		
	}

}
