package Grafos;

import java.util.LinkedList;

import Grafo.Nodo;

public class GrafoBFS {

	public GrafoBFS() {
		// TODO Auto-generated constructor stub
	}

	
	public void BFS(int s){
        boolean visitados[] = new boolean[4];
        LinkedList<Integer> cola = new LinkedList<Integer>();
        visitados[s]=true;
        cola.add(s);
 
        while (cola.size() != 0){
            s = cola.poll();
            System.out.print(s+" ");
            for (Vertice nodo : this.getVertice()){
                int n =  Integer.parseInt(nodo.getNombre());
                if (!visitados[n]){
                    visitados[n] = true;
                    cola.add(n);
                }
            }
        }
    }
}
