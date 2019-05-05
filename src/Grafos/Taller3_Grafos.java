package Grafos;

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
		
		
		
		
		g.dijkstra(0);
		
		
	}

}
