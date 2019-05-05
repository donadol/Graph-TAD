package Grafos;

public class Taller3_Grafos {

	public Taller3_Grafos() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LimiteException {
		// TODO Auto-generated method stub
		GrafosPrueba<String> g = new GrafosPrueba<String>();
		
		g.agregarVertice("popo1");
		g.agregarVertice("popo2");
		g.agregarVertice("popo3");
		g.agregarVertice("popo4");
		g.agregarVertice("popoNorman");
		
		g.agregarArista(1, 2, 2);
		g.agregarArista(2, 3, 1);
		g.agregarArista(1, 4, 5);
		
		System.out.println(g.obtenerCostoArista(2, 3));
	}

}
