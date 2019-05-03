package Grafos;

import java.util.ArrayList;
import java.util.List;

public class VerticeVecinos<T> extends Vertice<T> {

	private List<Arista <T>> vecinos;
	
	public VerticeVecinos() {
		// TODO Auto-generated constructor stub
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
		
		if(!existeVecino(destino)) {
			vecinos.remove(buscarVecino(destino));
			return true;
		}
		return false;
		
	}
	
	

}
