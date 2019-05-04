package Grafos;

import java.util.Comparator;

//Este comparador permite organizar la cola de prioridad

public class ComparadorPrim <T> implements Comparator <Vertice<T>>{

	@Override
	public int compare(Vertice<T> v1, Vertice<T> v2) {
		// TODO Auto-generated method stub
		if (v1.getDistancia() < v2.getDistancia()) return -1; 
        if (v1.getDistancia() > v2.getDistancia()) return 1; 
        else return 0;
	}

	

	
}


