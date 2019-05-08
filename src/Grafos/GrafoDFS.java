package Grafos;

import java.util.List;


public class GrafoDFS {

	public GrafoDFS() {
		// TODO Auto-generated constructor stub
	}

	public void DFS(Vertice v, List<Vertice> nueva) {
		if (!nueva.contains(v)) {
			nueva.add(v);
			for (int i = 0; i < v.getVecinos().size(); i++) {
				Vertice a = v.getContenido(i);//Corregir para buscar la forma de obtener al vertice en pos i
				if (!nueva.contains(a)) {
					DFS(a, nueva);
				}
			}
		}
	}
	
}
