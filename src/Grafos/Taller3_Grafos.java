package Grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Taller3_Grafos {

	public Taller3_Grafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LimiteException {
		// TODO Auto-generated method stub
		GrafosPrueba<String> g = new GrafosPrueba<String>();
		g.agregarVertice("0");
		g.agregarVertice("1");
		g.agregarVertice("2");
		g.agregarVertice("3");

		g.agregarArista(0,1,100);
		g.agregarArista(1,2,16);
		g.agregarArista(2,0,50);
		g.agregarArista(2,3,1);
		//g.imprimirGrafoTexto();

		System.out.println("HOLA");
		System.out.println(g.obtenerCostoArista(2, 2));
		System.out.println(g.obtenerCostoArista(0, 1));
		System.out.println("---------------");


		for(Vertice<String> v : g.obtenerVecinos(2)) {
			System.out.println(v.getContenido());
		}
		for(Arista<String> v : g.obtenerAristas()) {
			System.out.println(v.getCosto());
		}



		//IMAGEN GRAFO: https://www.geeksforgeeks.org/wp-content/uploads/Fig-11.jpg
		GrafosPrueba<String> grafoPrueba = new GrafosPrueba<String>();
		grafoPrueba.agregarVertice("0"); //0
		grafoPrueba.agregarVertice("1"); //1
		grafoPrueba.agregarVertice("2"); //2
		grafoPrueba.agregarVertice("3"); //3
		grafoPrueba.agregarVertice("4"); //4
		grafoPrueba.agregarVertice("5"); //5
		grafoPrueba.agregarVertice("6"); //6 
		grafoPrueba.agregarVertice("7"); //7
		grafoPrueba.agregarVertice("8"); //8


		grafoPrueba.agregarArista(0, 1, 4);
		grafoPrueba.agregarArista(0, 7, 8);

		grafoPrueba.agregarArista(1, 0, 4);
		grafoPrueba.agregarArista(1, 2, 8);
		grafoPrueba.agregarArista(1, 7, 11);


		grafoPrueba.agregarArista(2, 1, 8);
		grafoPrueba.agregarArista(2, 5, 4);
		grafoPrueba.agregarArista(2, 8, 2);
		grafoPrueba.agregarArista(2, 3, 7);


		grafoPrueba.agregarArista(3, 2, 7);
		grafoPrueba.agregarArista(3, 4, 9);
		grafoPrueba.agregarArista(3, 5, 14);


		grafoPrueba.agregarArista(4, 3, 9);
		grafoPrueba.agregarArista(4, 5, 10);


		grafoPrueba.agregarArista(5, 3, 14);
		grafoPrueba.agregarArista(5, 2, 4);
		grafoPrueba.agregarArista(5, 4, 10);
		grafoPrueba.agregarArista(5, 6, 2);


		grafoPrueba.agregarArista(6, 7, 1);
		grafoPrueba.agregarArista(6, 5, 2);
		grafoPrueba.agregarArista(6, 8, 6);


		grafoPrueba.agregarArista(7, 0, 8);
		grafoPrueba.agregarArista(7, 1, 11);
		grafoPrueba.agregarArista(7, 6, 1);
		grafoPrueba.agregarArista(7, 8, 7);


		grafoPrueba.agregarArista(8, 7, 7);
		grafoPrueba.agregarArista(8, 6, 6);
		grafoPrueba.agregarArista(8, 2, 2);





		//IMAGEN RESULTADO DIJKSTRA: https://www.geeksforgeeks.org/wp-content/uploads/DIJ5.jpg
		Map <Integer, Vertice<String>> resultadoDijkstra = grafoPrueba.dijkstra(0);
		System.out.println (resultadoDijkstra.get(5).getContenido());
		pruebaKruskal(grafoPrueba);
	}

	static public void pruebaKruskal(Grafo<String> g) {
		List<Arista<String>> result = g.KruskalMST();
		System.out.println("Vertices en el MST con Kruskal"); 
		for (int i = 0; i < result.size(); ++i) 
			System.out.println(result.get(i).getOrigen().getContenido()+" -- " + result.get(i).getDestino().getContenido()+" == " + result.get(i).getCosto()); 
	}

}
