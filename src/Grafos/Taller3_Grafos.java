package Grafos;

public class Taller3_Grafos {

	public Taller3_Grafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LimiteException {
		// TODO Auto-generated method stub
		GrafosPrueba<String> g = new GrafosPrueba<String>();
		
		GrafoReal<String> grafo = new GrafoReal<String>();
		
		grafo.agregarVertice("popo1");
		grafo.agregarVertice("popo2");
		grafo.agregarVertice("popo3");
		grafo.agregarVertice("popo4");
		grafo.agregarVertice("popoNorman");
		
		grafo.agregarArista(1, 2, 2);
		grafo.agregarArista(2, 3, 1);
		grafo.agregarArista(1, 4, 5);
		
		System.out.println(grafo.obtenerCostoArista(2, 3));
	}

}
