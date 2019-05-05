package Grafos;

import java.util.*;

public abstract class Grafo <T>{

	protected static int INF = Integer.MAX_VALUE;
	protected int NumeroVertices;
	Map <Integer, Vertice<T>> vertices;
	

	public Grafo() {
		this.NumeroVertices = 0;
		vertices = new HashMap <Integer, Vertice<T>> ();
		
	}

	public abstract int agregarVertice(T contenido);
	public abstract int eliminarVertice(int identificador);

	public abstract boolean agregarArista (int origen, int destino, int costo);
	public abstract boolean eliminarArista (int origen, int destino);

	public abstract List<Vertice<T>> obtenerVecinos (int identificador);
	public abstract List<Vertice<T>> obtenerVecinos (Vertice <T> vertice);

	//Funcion que retorna todas las aristas de un grafo 
	public abstract List<Arista<T>> obtenerAristas ();

	//Función que retorna el costo de una arista dado el origen y destino
	public abstract int obtenerCostoArista (int origen, int destino) throws LimiteException;
	public abstract int obtenerCostoArista (Vertice <T> origen, Vertice <T> destino) throws LimiteException;

	//Dijkstra: Permite encontrar el camino más corto de un vertice a todos los demás vertices
	//Retorna: Un mapa donde cada con cada identificador del vertice, se puede obtener el vertice previo que sigue el camino más corto

	public Map<Integer, Vertice<T>> dijkstra (int inicio) throws LimiteException{
		Vertice<T> v;
		//Reinicia los valores de distancia de cada uno de los vertices
		reiniciarDistancias();
		reiniciarMarcas();

		//Crea el mapa resultante, el inicio tiene como previo un valor nulo
		Map <Integer, Vertice<T>> previo = new HashMap <Integer, Vertice<T>>();
		previo.put(inicio, null);

		//El vertice de inicio tiene una distancia 0 (porque es él mismo)
		System.out.println("TAMAÑO: "+vertices.size());
		vertices.get(inicio).setDistancia(0);		
		vertices.get(inicio).setMarcado(true);	

		PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<Vertice<T>>(vertices.size(), new ComparadorDijkstra<T> ());
		colaPrioridad.add(vertices.get(inicio));

		while (!colaPrioridad.isEmpty()) {
			v = colaPrioridad.remove();
			v.setMarcado(true);
			for (Vertice<T> u: obtenerVecinos(v)) {
				if (!u.isMarcado()) {
					if (u.getDistancia() > v.getDistancia() + obtenerCostoArista(v, u)) {
						u.setDistancia(v.getDistancia() + obtenerCostoArista(v, u));

						previo.put(u.getIdentificador(), v);
						colaPrioridad.offer(u);
					}
				}
			}
		}
		return previo;
	}

	//es necesario meter todos los vertices!
	//se inicia desde cualquier vertice

	public Map<Integer, Vertice<T>> prim(int inicio) throws LimiteException{
		Vertice<T> v;
		//Reinicia los valores de distancia de cada uno de los vertices
		reiniciarDistancias();
		reiniciarMarcas();

		//Crea el mapa resultante, el inicio tiene como previo un valor nulo
		Map <Integer, Vertice<T>> previo = new HashMap <Integer, Vertice<T>>();
		previo.put(inicio, null);

		//El vertice de inicio tiene una distancia 0 (porque es él mismo)
		vertices.get(inicio).setDistancia(0);		
		vertices.get(inicio).setMarcado(true);	

		PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<Vertice<T>>(vertices.size(), new ComparadorDijkstra<T> ());
		colaPrioridad.add(vertices.get(inicio));

		while (!colaPrioridad.isEmpty()) {
			v = colaPrioridad.remove();
			v.setMarcado(true);
			for (Vertice<T> u: obtenerVecinos(v)) {
				if (!u.isMarcado()) {
					if (u.getDistancia() > obtenerCostoArista(v, u)) {
						u.setDistancia(obtenerCostoArista(v, u));		
						previo.put(u.getIdentificador(), v);
						colaPrioridad.offer(u);
					}
				}
			}
		}	
		return previo;
	}

	public void BFS (int identificador) {

	}

	public void reiniciarMarcas () {
		for (Vertice<T> vertice: vertices.values()) {
			vertice.setMarcado(false);
		}
	}

	//Para los algoritmos de Dijkstra y Prim, coloca las distancias de cada uno de los vertices en infinito.
	public void reiniciarDistancias() {
		for (Vertice<T> vertice: vertices.values()) {
			vertice.setDistancia(INF);
		}
	}
	// Encuentra el set del vertice i
	public int find(Subset[] s, int i) {
		if(s[i].parent!=i)
			s[i].parent=find(s, s[i].parent);
		return s[i].parent;
	}
	// Une el set de los vertices x e y
	public void union(Subset[] s, int x, int y) {
		int xroot = find(s, x); 
		int yroot = find(s, y); 
		if (s[xroot].rank < s[yroot].rank) 
			s[xroot].parent = yroot; 
		else if (s[xroot].rank > s[yroot].rank) 
			s[yroot].parent = xroot; 
		else{ 
			s[yroot].parent = xroot; 
			s[xroot].rank++; 
		}
	}
	
	// KruskalMST permite encontrar el árbol de recubrimiento minimo de un grafo utilizando el algoritmo de kruskal
	// Retorna una lista con pertenencientes al árbol
	public List<Arista<T>> KruskalMST() {
		List<Arista<T>> results = new ArrayList<Arista<T>>(vertices.size());
		List<Arista<T>> aristas = obtenerAristas();
		int e=0, i=0;
		Collections.sort(aristas);
		Subset subsets[] = new Subset[vertices.size()];
		for(int v=0; v<vertices.size(); ++i) {
			subsets[v] = new Subset(v,0);
		}
		while(e<vertices.size()-1) {
			Arista<T> next = new Arista<T>();
			next = aristas.get(i);
			i++;
			int x = find(subsets, next.origen.getIdentificador());
			int y = find(subsets, next.destino.getIdentificador());
			if(x!=y) {
				results.set(e, next);
				union(subsets, x, y);
				e++;
			}	
		}
		return results;
	}
}
