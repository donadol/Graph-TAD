package Grafos;

import java.util.*;
import java.util.Map.Entry;

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
		
		
		
	 
		Arista<T> aristaMST;
		
		
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
	// Retorna una lista con aristas pertenencientes al árbol
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

	// FloydWarshall permite obteneruna matriz con las minimas distancias entre todos los nodos
	public Double[][] FloydWarshall(){
		
		int n = vertices.size();
		Double [][] c = new Double [n][n];
		Double [][] matrizRetorno = new Double [n][n];
		Arista<T> aristaAux = new Arista<T>();
		int i = 0;
		int j = 0;
		
		for(Entry<Integer, Vertice<T>> verticeOrigen : vertices.entrySet()) {
			j = 0;
			for(Entry<Integer, Vertice<T>> verticeDestino : vertices.entrySet()) {
				aristaAux = obtenerArista(verticeOrigen.getValue().getIdentificador(),verticeDestino.getValue().getIdentificador());
				if(aristaAux != null) {
					matrizRetorno[i][j] = (double) aristaAux.getCosto();
				}else if(i == j) {
					matrizRetorno[i][j] = (double) 0;
				}else {
					matrizRetorno[i][j] = (double) Double.POSITIVE_INFINITY;
				}
				j++;
			}
			i++;
		}
		
		
		i = 0;
		j = 0;
		
		for( i = 0; i < n; i ++)
			for( j = 0; j < n; j ++)
				c[i][j] = matrizRetorno[i][j];
		
		for(int k = 0; k < n; k ++)
			for( i = 0; i < n; i ++)
				for( j = 0; j < n; j ++)
					c[i][j] = min( c[i][j], ( c[i][k] +  c[k][j]));
		
		return c;	
	}
	
	// BellmanFord permite obtener una lista de la distancia entreun vertice inicial y todos los demas
	//Permite ademas realizar el calculo cuando hay aristas negativas e identificar ciclos negativos en el grafo
	public int[] BellmanFord(int verticeInicicial) 
	    { 
			ArrayList<Arista<T>> listaAristas = new ArrayList<Arista<T>>();
			listaAristas = (ArrayList<Arista<T>>) obtenerAristas();
	        int V = vertices.size();
	        int E = listaAristas.size(); 
	        int dist[] = new int[V];
	        int posicionInicial = posVertice(verticeInicicial);
	  
	        for (int i = 0; i < V; i++) 
	            dist[i] = INF;
	        dist[posicionInicial] = 0; 
	  
	        for (int i = 1; i < V; i++) { 
	            for (int j = 0; j < E; j++) { 
	                int u = posVertice(listaAristas.get(j).getOrigen().getIdentificador()); 
	                int v = posVertice(listaAristas.get(j).getDestino().getIdentificador()); 
	                int weight = listaAristas.get(j).getCosto(); 
	                if (dist[u] != INF && dist[u] + weight < dist[v]) 
	                    dist[v] = dist[u] + weight; 
	            } 
	        } 
	  
	        for (int j = 0; j < E; j++) 
	        { 
	            int u = posVertice(listaAristas.get(j).getOrigen().getIdentificador()); 
	            int v = posVertice(listaAristas.get(j).getDestino().getIdentificador()); 
	            int weight = listaAristas.get(j).getCosto(); 
	            if (dist[u] != INF &&  dist[u]+weight < dist[v]) 
	              System.out.println("El grafo contine un ciclo de costo negativo!"); 
	        } 
	        return dist; 
	    } 
	
	
	//min. Funcion auxiliar que retornael minimo entre dos numeros
	protected Double min(double a, double b) {
		if(a >= b)
			return  b;
		else
			return  a;	
	}
	
	//posVertice. Funcion auxiliar que dado un identificador, retorna su posicion en el maap de vertices
	protected int posVertice(int identificador) {
		
		int i = 0;
		for(Entry<Integer, Vertice<T>> entry : vertices.entrySet()) {
			if(entry.getValue().getIdentificador() == identificador)
				return i;
			i++;
		}
		return -1;
	}
	
	//existeConexionDirecta es una funcion que permite saber si dosvertices estan conectados directamente
	public boolean existeConexionDirecta(int idVerticeA, int idVerticeB) {

		if(existeVertice(idVerticeA) && existeVertice(idVerticeB)) {
			for(Arista<T> a : obtenerAristasVertice(idVerticeA))
				if(a.getDestino().getIdentificador() == idVerticeB)
					return true;
		}
		return false;
	}

	//existeVertice permite saber si un vertice existe o no en el grafo
	private boolean existeVertice(int idVertice) {
		
		if(vertices.get(idVertice) != null)
			return true;
		else
			return false;
	}

	//existeConexionDirecta es una funcion que permite saber si dosvertices estan conectados directa o indirectamente
	public boolean existeConexionDirectaOIndirecta(int idVerticeA, int idVerticeB) {
		
		boolean band = existeConexionDirecta(idVerticeA, idVerticeB);
		
		if(!band) {
			if(existeVertice(idVerticeA) && existeVertice(idVerticeB)) {
				ArrayList<Arista<T>> listaVecinosAux =  (ArrayList<Arista<T>>) obtenerAristasVertice(idVerticeA);
				vertices.get(idVerticeA).setMarcado(true);
				for(Arista<T> p : listaVecinosAux) {
					if(p.getDestino().isMarcado() == false) {
						return existeConexionDirectaOIndirecta(p.getDestino().getIdentificador(), idVerticeB);
					}
				}
			}
		} else 
			return true;
		
		reiniciarMarcas();
		
		return band;
	}
	
	//existeAlgunVerticeConectadoATodos funcion permite saber si hay un vertice que conecte  todos los demas y lo retorna
	public Vertice<T> existeAlgunVerticeConectadoATodos(){
		
		boolean band = true;
		for (Vertice<T> v : vertices.values()) {
			for (Vertice<T> v2 : vertices.values()) {
				if(v.getIdentificador() != v2.getIdentificador()) {
					band = band && existeConexionDirectaOIndirecta(v.getIdentificador(), v2.getIdentificador());
					if(band == false) {
						break;
					}
				}
			}
			if(band == true) {
				return v;
			}
			band = true;
			}
			return null;
	}
	
	//arbolDeMinimaExpancionConVerticesHojas sirve para armar un MST cuyas ojas deben contener unos vertices indicados prevaimente
	public List<Arista<T>> arbolDeMinimaExpancionConVerticesHojas(ArrayList<Vertice<T>> subSet){
		
		ArrayList<Arista<T>> aristas = new ArrayList<Arista<T>>();
		ArrayList<Arista<T>> aristas2 = new ArrayList<Arista<T>>();
		aristas = (ArrayList<Arista<T>>) obtenerAristas();
		aristas2 = (ArrayList<Arista<T>>) obtenerAristas();
		for(Arista<T> a : aristas) {
			if(existeVerticeEnSubset(a.getOrigen().getIdentificador(), subSet)) {
				aristas2.remove(a);
			}
		}
		return MST(aristas2);
	}	
	
	
	public List<Arista<T>> MST(List<Arista<T>> aristas) {
		List<Arista<T>> results = new ArrayList<Arista<T>>();
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
	public boolean existeVerticeEnSubset(int indicadorABuscar, ArrayList<Vertice<T>> subSet){
		for(Vertice<T> v : subSet) {
			if(indicadorABuscar == v.getIdentificador())
				return true;	
		}
		return false;
	}

	//imprimirGrafo imprime el grafo para poder verlo deuna forma mas visual
	public void imprimirGrafo() {
		
		ArrayList<Integer> identificadores = new ArrayList<Integer>();
		int n = vertices.size();
		Double [][] matrizRetorno = new Double [n][n];
		Arista<T> aristaAux = new Arista<T>();
		int i = 0;
		int j = 0;
		for(Entry<Integer, Vertice<T>> verticeOrigen : vertices.entrySet()) {
			j = 0;
			identificadores.add(verticeOrigen.getValue().getIdentificador());
			for(Entry<Integer, Vertice<T>> verticeDestino : vertices.entrySet()) {
				aristaAux = obtenerArista(verticeOrigen.getValue().getIdentificador(),verticeDestino.getValue().getIdentificador());
				if(aristaAux != null) {
					matrizRetorno[i][j] = (double) aristaAux.getCosto();
				}else if(i == j) {
					matrizRetorno[i][j] = (double) 0;
				}else {
					matrizRetorno[i][j] = (double) Double.POSITIVE_INFINITY;
				}
				j++;
			}
			i++;
		}
		System.out.println(" ");
		for(i = 0; i < n; i++) {
			System.out.print( "id " + identificadores.get(i)+ ":    ");
			for(j = 0; j < n; j++) {
				if(matrizRetorno[i][j] != INF) {
					System.out.print(matrizRetorno[i][j] + "         ");
				}else {
					System.out.print("INF" + "         ");
				}
			}
			System.out.println(" ");
			System.out.println(" ");
		}
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
	}
}
