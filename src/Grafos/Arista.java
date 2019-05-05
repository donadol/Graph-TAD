package Grafos;

public class Arista <T> implements Comparable<Arista<T>>{
	private Vertice<T> origen;
	private Vertice<T> destino;
	private int costo;
	
	public Arista() {}
	public Arista(Vertice<T> origen, Vertice<T> destino, int costo) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.costo = costo;
	}
	public Vertice<T> getOrigen() {
		return origen;
	}
	public void setOrigen(Vertice<T> origen) {
		this.origen = origen;
	}
	public Vertice<T> getDestino() {
		return destino;
	}
	public void setDestino(Vertice<T> destino) {
		this.destino = destino;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	public int compareTo(Arista<T> o) {
		return this.costo-o.costo;
	}
}