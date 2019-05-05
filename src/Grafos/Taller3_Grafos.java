package Grafos;

public class Taller3_Grafos {

	public Taller3_Grafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LimiteException {
		// TODO Auto-generated method stub
		
		GrafoReal<String> grafo = new GrafoReal<String>();
		
		
		grafo.agregarVertice("popo1",1);
		grafo.agregarVertice("popo2",2);
		grafo.agregarVertice("popo3",3);
		grafo.agregarVertice("popo4",4);
		grafo.agregarVertice("popoNorman",5);
		
		grafo.agregarArista(1, 2, 2);
		grafo.agregarArista(2, 3, 1);
		grafo.agregarArista(1, 4, 5);
		
		System.out.println(grafo.obtenerCostoArista(2, 3));
		
		
		
		
	}

}
