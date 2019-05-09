package Recipientes;

import java.util.*;

import GrafoMatriz.GrafoMatriz;
import Grafos.Nodo;
import Grafos.Vertice;

public class GrafoRecipientes extends GrafoMatriz<EstadoRecipientes>{
	
	public GrafoRecipientes() {
		super();
		inicializarGrafo();
	}

	public void inicializarGrafo() {
		crearVertices();
		generarConexiones();
	}
	
	public void crearVertices() {
		for(int i=0; i<=7; ++i) {
			this.agregarVertice(new EstadoRecipientes(4, 7-i, i));
		}
		for(int i=3; i>=1; --i) {
			this.agregarVertice(new EstadoRecipientes(i, 0, 11-i));
		}
		for(int i=10; i>=4; --i) {
			this.agregarVertice(new EstadoRecipientes(0, 11-i, i));
		}
		for(int i=1; i<4; ++i) {
			this.agregarVertice(new EstadoRecipientes(i, 7, 4-i));
		}
	}
	
	public void generarConexiones() {
		for(int i=0; i<=7; ++i) {
			if(i!=0) {
				this.agregarArista(i, 0, 1);
				this.agregarArista(i, 21-i, 1);
			}
			this.agregarArista(i, 17-i, 1);
			if(i!=7)
				this.agregarArista(i, 7, 1);
		}
		for(int i=8; i<=10; ++i) {
			this.agregarArista(i, 7, 1);
			this.agregarArista(i, 28-i, 1);
			this.agregarArista(i, 21-i, 1);
			if(i!=10)
				this.agregarArista(i, 10, 1);
		}
		for(int i=11; i<=17; ++i) {
			if(i!=11)
				this.agregarArista(i, 11, 1);
			this.agregarArista(i, 21-i, 1);
			this.agregarArista(i, 17-i, 1);
			if(i!=17)
				this.agregarArista(i, 17, 1);
		}
		for(int i=18; i<=20; ++i) {
			this.agregarArista(i, 17, 1);
			this.agregarArista(i, 28-i, 1);
			this.agregarArista(i, 21-i, 1);
			this.agregarArista(i, 0, 1);
		}
	}
	
	public List<Vertice<EstadoRecipientes>> construirSecuencia(){
		List<Vertice<EstadoRecipientes>> camino = new ArrayList<Vertice<EstadoRecipientes>>();
		List<Nodo<EstadoRecipientes>> path=this.DFS(0);
		int pos =-1, pid=-1;
		for(int i=0; i<path.size(); ++i) {
			if(path.get(i).getV().getContenido().getA()==2 ||  path.get(i).getV().getContenido().getB()==2) {
				pos=i;
				pid=path.get(i).getPid();
				camino.add(path.get(i).getV());
				break;
			}
		}
		if(pos==-1)
			return null;
		for(int i=pos-1; i>=0; --i) {
			if(path.get(i).getV().getIdentificador()==pid) {
				camino.add(path.get(i).getV());
				pid=path.get(i).getPid();
			}
		}
		return camino;
	}
	
	public void imprimirSecuencia() {
		List<Vertice<EstadoRecipientes>> camino = this.construirSecuencia();
		if(camino!=null) {
			Collections.reverse(camino);
			System.out.println("A B C");
			for(Vertice<EstadoRecipientes> n: camino) {
				System.out.println(n.getContenido().toString());
			}
		}
		else {
			System.out.println("No existe camino");
		}
	}
}
