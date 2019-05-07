package Grafos;

import java.util.List;

import Grafo.Nodo;

public class GrafoDFS {

	public GrafoDFS() {
		// TODO Auto-generated constructor stub
	}

	public void DFS(Vertice v, List<Vertice> nueva) {
		if (!nueva.contains(v)) {
			nueva.add(v);
			for (int i = 0; i < v.getVertice().size(); i++) {
				Vertice a = v.getVertice().get(i);
				if (!nueva.contains(a)) {
					DFS(a, nueva);
				}
			}
		}
	}
	
}
