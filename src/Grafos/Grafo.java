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
	
	
	//Función que dados dos vertices retorna una arista entre ellos
	//Puede retornar nulo si la arista entre los dos vertices no existe
	public abstract Arista<T> obtenerArista (int origen, int destino);
	public abstract Arista<T> obtenerArista (Vertice<T> origen, Vertice<T> destino);
	
	
	//Función que retorna la lista de aristas para un vertice dado
	public abstract List<Arista<T>> obtenerAristasVertice (int identificador);
	public abstract List<Arista<T>> obtenerAristasVertice (Vertice<T> vertice);
	
	
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

	public List<Arista<T>> prim(int inicio) throws LimiteException{
		List<Arista<T>> MST = new ArrayList<Arista<T>>();
		Vertice<T> v;
		
		
		//Reinicia los valores de distancia de cada uno de los vertices
		reiniciarDistancias();
		reiniciarMarcas();

		//Crea el mapa resultante, el inicio tiene como previo un valor nulo
		Map <Integer, Vertice<T>> previo = new HashMap <Integer, Vertice<T>>();
		previo.put(inicio, null);

		//El vertice de inicio tiene una distancia 0 (porque es él mismo)	
		vertices.get(inicio).setMarcado(true);	

		
		
		//Se colocan las aristas del vertice de inicio
		
		PriorityQueue<Arista<T>> colaPrioridad = new PriorityQueue<Arista<T>>(vertices.size(), new ComparadorPrim<T> ());
		Vertice <T> verticeInicio = vertices.get(inicio);
		for (Vertice<T> u: obtenerVecinos (verticeInicio)) {
			Arista<T> arista = new Arista<T>();
			arista.setCosto(obtenerCostoArista(verticeInicio, u));
			arista.setOrigen(verticeInicio);
			arista.setDestino(u);
			
			colaPrioridad.offer(arista);
		}
		
		
		
	 
		Arista aristaMST;
		
		
		while (!colaPrioridad.isEmpty()) {
			aristaMST = colaPrioridad.remove();
			if (!aristaMST.getDestino().isMarcado()) {
				MST.add (aristaMST);
				aristaMST.getDestino().setMarcado(true);
				
				v = aristaMST.getDestino();
				//v.setMarcado(true);
				
				for (Vertice<T> u: obtenerVecinos(v)) {
					if (!u.isMarcado()) {
						
							
							Arista<T> arista = new Arista<T>();
							arista.setCosto(obtenerCostoArista(v, u));
							arista.setOrigen(v);
							arista.setDestino(u);
										
							colaPrioridad.offer(arista);
						
					}
				}
			}
			
			
		}	
		return MST;
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
		List<Arista<T>> results = new ArrayList<Arista<T>>();
		List<Arista<T>> aristas = obtenerAristas();
		int e=0, i=0;
		Collections.sort(aristas);
		Subset subsets[] = new Subset[vertices.size()];
		for(int v=0; v<vertices.size(); ++v) {
			subsets[v] = new Subset(v,0);
		}
		while(e<vertices.size()-1) {
			Arista<T> next = aristas.get(i);
			i++;
			int x = find(subsets, next.getOrigen().getIdentificador());
			int y = find(subsets, next.getDestino().getIdentificador());
			if(x!=y) {
				results.add(next);
				union(subsets, x, y);
				e++;
			}	
		}
		return results;
	}
	
	public abstract void imprimirGrafo();
}
