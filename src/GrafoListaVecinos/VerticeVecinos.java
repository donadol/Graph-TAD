package GrafoListaVecinos;

import java.util.ArrayList;
import java.util.List;

import Grafos.Arista;
import Grafos.Vertice;

public class VerticeVecinos<T> extends Vertice<T> {
	private List<Arista <T>> vecinos;
	
	public VerticeVecinos() {
		super();
		vecinos = new ArrayList<Arista<T>>();
	}
	public List<Arista<T>> getVecinos() {
		return vecinos;
	}
	public void setVecinos(List<Arista<T>> vecinos) {
		this.vecinos = vecinos;
	}
	public Arista <T> buscarVecino(int idVecino) {
		if(existeVecino(idVecino)) {
			for(Arista <T> p : vecinos) {
				if(p.getDestino().getIdentificador() == idVecino)
					return p;
			}
		}
		return null;
	}
	public boolean existeVecino(int idVecino) {
		for(Arista <T> a : vecinos) {
			if(a.getDestino().getIdentificador() == idVecino)
				return true;
		}
		return false;
	}
	public boolean agregarVecino(Arista<T> vecinoNuevo) {
		
		if(!existeVecino(vecinoNuevo.getDestino().getIdentificador())) {
			vecinos.add(vecinoNuevo);
			return true;
		}
		return false;
		
	}
	public boolean eliminarVecino(int destino) {
		
		if(existeVecino(destino)) {
			vecinos.remove(buscarVecino(destino));
			return true;
		}
		return false;
		
	}
	public void imprimirVecinos() {
		System.out.println("Soy el nodo con id: " + getIdentificador() + ", contenido: "+ getContenido().toString()+", y estos son mis vecinos: ");
		for(Arista<T>  p : vecinos) {
			System.out.println("	vecino de id: "+ p.getDestino().getIdentificador() + ", con contenido: "
			+ p.getDestino().getContenido().toString() + ", y una conexion de peso: " + p.getCosto());
		}
		System.out.println("----------------------------------------------------------------" );
		
	}
}