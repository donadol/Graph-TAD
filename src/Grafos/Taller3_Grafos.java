package Grafos;

public class Taller3_Grafos {

	public Taller3_Grafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LimiteException {
		// TODO Auto-generated method stub
		GrafosPrueba<String> g = new GrafosPrueba<String>();
		
		g.agregarVertice("ver1");
		g.agregarVertice("ver2");
		g.agregarVertice("ver3");
		g.agregarVertice("ver4");
		
		g.agregarArista(1, 2, 2);
		g.agregarArista(2, 3, 1);
		
		
		System.out.println(g.obtenerCostoArista(1, 2));
	}

}
