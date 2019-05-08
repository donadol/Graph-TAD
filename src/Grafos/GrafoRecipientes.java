package Grafos;

import java.util.*;

public class GrafoRecipientes extends GrafoMatriz<EstadoRecipientes>{
	private List<Integer> posiblesFinales;
	
	public GrafoRecipientes() {
		super();
		posiblesFinales=new ArrayList<Integer>();
		inicializarGrafo();
	}

	public void inicializarGrafo() {
		crearVertices();
		generarConexiones();
	}
	
	public void crearVertices() {
		int x;
		for(int i=0; i<=7; ++i) {
			x=this.agregarVertice(new EstadoRecipientes(4, 7-i, i));
			if(7-i==2)
				posiblesFinales.add(x);
		}
		for(int i=4; i<=10; ++i) {
			x=this.agregarVertice(new EstadoRecipientes(0, 11-i, i));
			if(11-i==2)
				posiblesFinales.add(x);
		}
		for(int i=1; i<4; ++i) {
			x=this.agregarVertice(new EstadoRecipientes(i, 7, 4-i));
			if(i==2)
				posiblesFinales.add(x);
		}
		for(int i=1; i<4; ++i) {
			x=this.agregarVertice(new EstadoRecipientes(i, 0, 11-i));
			if(i==2)
				posiblesFinales.add(x);
		}
	}
	
	public void generarConexiones() {
		for (Map.Entry<Integer,Vertice<EstadoRecipientes>> x : this.vertices.entrySet())  {
			for (Map.Entry<Integer,Vertice<EstadoRecipientes>> y : this.vertices.entrySet())  {
				if(!x.equals(y)) {
					// revisar si la diferencia es echar agua de uno a otro y si si crear la conexión!
				}
			}
		}
	}
}
