package Grafos;

import java.util.Comparator;

//Este comparador permite organizar la cola de prioridad

public class ComparadorPrim <T> implements Comparator <Arista<T>>{

	@Override
	public int compare(Arista<T> a1, Arista<T> a2) {
		// TODO Auto-generated method stub
		if (a1.getCosto() < a2.getCosto()) return -1; 
        if (a1.getCosto() > a2.getCosto()) return 1; 
        else return 0;
	}

	

	
}


