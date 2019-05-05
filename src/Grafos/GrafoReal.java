package Grafos;

import java.util.List;

public class GrafoReal<T> extends GrafoAristas<T>{

	public GrafoReal() {
		// TODO Auto-generated constructor stub
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
	public List<Vertice<T>> obtenerVecinos(int identificador) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Vertice<T>> obtenerVecinos(Vertice<T> vertice) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	


}
