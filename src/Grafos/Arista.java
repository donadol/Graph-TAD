package Grafos;

public class Arista <T>  {
	Vertice<T> origen;
	Vertice<T> destino;
	int costo;
	
	
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
	
	
}
