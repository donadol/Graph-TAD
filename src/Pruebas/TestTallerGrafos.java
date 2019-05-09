package Pruebas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import Grafos.Arista;
import Grafos.Grafo;
import Grafos.LimiteException;
import Grafos.Nodo;
import Grafos.Vertice;
import TigreBurroPaja.EstadoTigreBurroPaja;
import TigreBurroPaja.GrafoTigreBurroPaja;

public class TestTallerGrafos {

	public TestTallerGrafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LimiteException, CloneNotSupportedException {
		probarPunto1();
	}

	private static <T> void probarPunto1() throws LimiteException, CloneNotSupportedException {
		TestGrafoListaVecinos<String> grafoListaVecinos = new TestGrafoListaVecinos<String>();

		//IMAGEN GRAFO: https://www.geeksforgeeks.org/wp-content/uploads/Fig-11.jpg
		grafoListaVecinos.agregarVertice("0"); //0
		grafoListaVecinos.agregarVertice("1"); //1
		grafoListaVecinos.agregarVertice("2"); //2
		grafoListaVecinos.agregarVertice("3"); //3
		grafoListaVecinos.agregarVertice("4"); //4
		grafoListaVecinos.agregarVertice("5"); //5
		grafoListaVecinos.agregarVertice("6"); //6 
		grafoListaVecinos.agregarVertice("7"); //7
		grafoListaVecinos.agregarVertice("8"); //8


		grafoListaVecinos.agregarArista(0, 1, 4);
		grafoListaVecinos.agregarArista(0, 7, 8);

		grafoListaVecinos.agregarArista(1, 0, 4);
		grafoListaVecinos.agregarArista(1, 2, 8);
		grafoListaVecinos.agregarArista(1, 7, 11);


		grafoListaVecinos.agregarArista(2, 1, 8);
		grafoListaVecinos.agregarArista(2, 5, 4);
		grafoListaVecinos.agregarArista(2, 8, 2);
		grafoListaVecinos.agregarArista(2, 3, 7);


		grafoListaVecinos.agregarArista(3, 2, 7);
		grafoListaVecinos.agregarArista(3, 4, 9);
		grafoListaVecinos.agregarArista(3, 5, 14);


		grafoListaVecinos.agregarArista(4, 3, 9);
		grafoListaVecinos.agregarArista(4, 5, 10);


		grafoListaVecinos.agregarArista(5, 3, 14);
		grafoListaVecinos.agregarArista(5, 2, 4);
		grafoListaVecinos.agregarArista(5, 4, 10);
		grafoListaVecinos.agregarArista(5, 6, 2);


		grafoListaVecinos.agregarArista(6, 7, 1);
		grafoListaVecinos.agregarArista(6, 5, 2);
		grafoListaVecinos.agregarArista(6, 8, 6);


		grafoListaVecinos.agregarArista(7, 0, 8);
		grafoListaVecinos.agregarArista(7, 1, 11);
		grafoListaVecinos.agregarArista(7, 6, 1);
		grafoListaVecinos.agregarArista(7, 8, 7);


		grafoListaVecinos.agregarArista(8, 7, 7);
		grafoListaVecinos.agregarArista(8, 6, 6);
		grafoListaVecinos.agregarArista(8, 2, 2);

		System.out.println ("Punto 1: Clase abstracta Grafo con todos los métodos vistos en clase.  Al menos deben estar presentes: dfs, bfs, dijkstra, floydWarshall, bellmanFord, kruskal, prim.");

		System.out.println ("-------DFS-------");
		System.out.println ();
		
		System.out.println ();

		System.out.println ("-------BFS-------");
		System.out.println ();
		
		System.out.println ();

		System.out.println ("-------Dijkstra-------");
		System.out.println ();
		//Resultado de Dijkstra (caminos más cortos) al grafo creado: https://www.geeksforgeeks.org/wp-content/uploads/DIJ5.jpg
		
		Map <Integer, Vertice<String>> resultadoDijkstra = grafoListaVecinos.dijkstra(0);
		construirCaminoDijkstra(grafoListaVecinos, resultadoDijkstra, 1, "Camino más corto desde vertice 0 hasta vertice 1");
		construirCaminoDijkstra(grafoListaVecinos, resultadoDijkstra, 5, "Camino más corto desde vertice 0 hasta vertice 5");
		construirCaminoDijkstra(grafoListaVecinos, resultadoDijkstra, 3, "Camino más corto desde vertice 0 hasta vertice 3");
		construirCaminoDijkstra(grafoListaVecinos, resultadoDijkstra, 8, "Camino más corto desde vertice 0 hasta vertice 8");	
		
		
		System.out.println ();


		System.out.println ("-------Floyd Warshall-------");
		System.out.println ();
		grafoListaVecinos.FloydWarshall();
		System.out.println ();
		
		
		System.out.println ("-------Bellman Ford-------");
		System.out.println ();
		for(int i = 0; i < 9; i++)
			System.out.println("valor "+ i + " es de " +grafoListaVecinos.BellmanFord(1)[i]);

		//Árbol MST para grafo creado: https://www.geeksforgeeks.org/wp-content/uploads/MST5.jpg
		System.out.println ();
		
		
		System.out.println ("-------Kruskal-------");
		System.out.println ();
		List<Arista<String>> MST;
		MST = grafoListaVecinos.KruskalMST();
		imprimirMST(MST, "MST con Kruskal");
		
		System.out.println ();
		
		
		System.out.println ("-------Prim-------");
		System.out.println ();
		//Prim funciona con cualquier vertice del grafo como vertice de inicio
		MST = grafoListaVecinos.prim(0);
		imprimirMST(MST, "MST con Prim");
		System.out.println ();
		
		
		
		System.out.println ("Punto 2. Al menos tres implementaciones también abstractas del Grafo: una con matriz de adyacencias, otra con lista de vecinos por vértice y otra con lista de aristas.");
		System.out.println ();
		System.out.println ("-- Se elaboraron las tres implementaciones y fueron utilizadas en distintos puntos.");
		System.out.println ();
		
		System.out.println ("Punto 3. Implementaciones convenientes para la pila (dfs), la cola (bfs), la cola de prioridad (Dijkstra, Prim) y el conjunto de vértices (Kruskal).");
		System.out.println ();
		System.out.println ("-- Para la implementación de DFS se usó la clase Stack de Java.");
		System.out.println ("-- Para la implementación de BFS se usó la clase Queue de Java.");
		System.out.println ("-- Para la implementación de Dijkstra se usó la clase PriorityQueue de Java, el criterio de prioridad es la mínima distancia al vertice de inicio.");
		System.out.println ("-- Para la implementación de Prim se usó la clase PriorityQueue de Java, el criterio de prioridad es la mínima distancia en los vecinos de un vertice.");
		System.out.println ();
		
		
		System.out.println ("Punto 4. Soluciones a los siguientes problemas. Cada problema debe ser resuelto con una clase principal que utilice explícitamente un grafo e invoque el o los métodos correspondientes. ");
		System.out.println ();
		System.out.println ("Punto 4.1: Tigre, burro y paja.  Elabore un programa que, utilizando un grafo, calcule e imprima la secuencia exacta de pasos de la solución de este problema.");
		System.out.println ();
		GrafoTigreBurroPaja grafoTigreBurroPaja = new GrafoTigreBurroPaja();
		Map<Integer, Vertice<EstadoTigreBurroPaja>> caminosSolucion = grafoTigreBurroPaja.dijkstra(0);
		
		System.out.println("Número de soluciones: " + grafoTigreBurroPaja.getIdentificadoresSolucion().size());
		System.out.println ();
		
		for (int i = 0; i<grafoTigreBurroPaja.getIdentificadoresSolucion().size(); i++) {
			construirCaminoDijkstra(grafoTigreBurroPaja, caminosSolucion, grafoTigreBurroPaja.getIdentificadoresSolucion().get(i), "Solución "+ (i+1));
		}
		System.out.println ();
		
		System.out.println ("4.2 Elabore un programa que, utilizando un grafo, resuelva la siguiente pregunta: Tenemos tres recipientes, de 4 l, 7 l y 10 l de capacidad.  Los dos primeros están llenos de agua y el tercero, vacío.  ¿Existe alguna secuencia de movimientos de líquido que deje 2 l en alguno de los dos primeros recipientes sin derramar agua?");
		System.out.println ();
		
		System.out.println ();
		System.out.println ("4.3 Arbitraje en cambio de monedas. Elabore un programa que, utilizando un grafo en el cual los vértices son monedas y las aristas sus tasas de cambio, calcule la mejor manera de cambiar una moneda a todas las otras.  Elabore otro programa que determine si existe una forma de obtener ganancias iniciando y terminando en una misma moneda.");
		System.out.println ();
		
		System.out.println ();
		System.out.println ("4.4 En un grafo dirigido, su programa debe determinar si hay un vértice desde donde se puede llegar a todos los demás.");
		System.out.println ();
		if(grafoListaVecinos.existeAlgunVerticeConectadoATodos() != null)
			System.out.println("Identificador del vertice: " + grafoListaVecinos.existeAlgunVerticeConectadoATodos().getIdentificador() + " contenido: "+ grafoListaVecinos.existeAlgunVerticeConectadoATodos().getContenido() );
		else
			System.out.println("No hay vertice que conecte a todos ");
		System.out.println ();
		

		System.out.println ("4.5 Dado un subconjunto de vértices de un grafo, calcule el árbol de cubrimiento más ligero donde todos los elementos del subconjunto son hojas.");
		System.out.println ();
		
		System.out.println ();
		


	}



	private static <T> void construirCaminoDijkstra(Grafo <T> grafo, Map<Integer, Vertice<T>> previo, int identificador, String etiqueta) {


		System.out.println(etiqueta+":");


		boolean caminoTerminado = false;
		List<T> caminoAlReves = new ArrayList<T>();
		caminoAlReves.add(grafo.obtenerVertice(identificador).getContenido());


		while (!caminoTerminado) {
			if (previo.get(identificador)!= null) {
				caminoAlReves.add(previo.get(identificador).getContenido());
				identificador = previo.get(identificador).getIdentificador();
			}
			else {
				caminoTerminado = true;
			}

		}

		Collections.reverse(caminoAlReves);

		for (T contenido: caminoAlReves) {
			System.out.println(contenido);
		}
		System.out.println();
	}
	
	private static <T> void imprimirMST (List<Arista<T>> aristasMST, String etiqueta) {
		System.out.println (etiqueta+":");
		System.out.println ("Número de aristas del árbol MST: "+ aristasMST.size());
		for (Arista <T> arista: aristasMST) {
			System.out.println ("Origen: "+ arista.getOrigen().getContenido() + " -- Destino: "+arista.getDestino().getContenido());
			
		}
	}
	
	static public void pruebaBFS() {
		Grafo<String> g = new TestGrafoMatriz<String>();
		g.agregarVertice("0");
		g.agregarVertice("1");
		g.agregarVertice("2"); //1
		g.agregarVertice("3"); //2
		g.agregarArista(0, 1, 1);
		g.agregarArista(0, 2, 1);
		g.agregarArista(1, 2, 1);
		g.agregarArista(2, 0, 1);
		g.agregarArista(2, 3, 1);
		g.agregarArista(3, 3, 1);
		
		List<Nodo<String>> res = g.BFS(2);
		for (Nodo<String> x : res)  {
			System.out.println(x.getV().getIdentificador());
		}
	}
	
	static public void pruebaDFS() {
		Grafo<String> g = new TestGrafoMatriz<String>();
		g.agregarVertice("0");
		g.agregarVertice("1");
		g.agregarVertice("2");
		g.agregarVertice("3"); 
		g.agregarVertice("4"); 
		g.agregarArista(1, 0, 1);
		g.agregarArista(0, 2, 1);
		g.agregarArista(2, 1, 1);
		g.agregarArista(0, 3, 1);
		g.agregarArista(1, 4, 1);
		
		List<Nodo<String>> res = g.DFS(0);
		for (Nodo<String> x : res)  {
			System.out.println(x.getV().getIdentificador());
		}
	}
}
