package TigreBurroPaja;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import GrafoListaVecinos.GrafoListaVecinos;
import Grafos.Vertice;

public class GrafoTigreBurroPaja extends GrafoListaVecinos<EstadoTigreBurroPaja> {

	// Representa los identificadores de los vertices solución, es decir, en los que
	// el Tigre, Burro y Paja están en el lado B
	
	private List<Integer> identificadoresSolucion;

	public GrafoTigreBurroPaja() throws CloneNotSupportedException {
		identificadoresSolucion = new ArrayList<Integer>();
		inicializarGrafo();
		
	}

	public Vertice<EstadoTigreBurroPaja> obtenerVertice(int identificador) {
		return this.vertices.get(identificador);
	}
	
	

	public List<Integer> getIdentificadoresSolucion() {
		return identificadoresSolucion;
	}

	public void setIdentificadoresSolucion(List<Integer> identificadoresSolucion) {
		this.identificadoresSolucion = identificadoresSolucion;
	}

	// crea el grafo con todas las posibilidades de movimiento en el problema
	public void inicializarGrafo () throws CloneNotSupportedException{
		Queue<Vertice<EstadoTigreBurroPaja>> colaEstados = new LinkedList<Vertice<EstadoTigreBurroPaja>>();

		//se inicia con los elementos y el barco en el lado A del río
		EstadoTigreBurroPaja estadoInicial = new EstadoTigreBurroPaja('A'); 
		estadoInicial.agregarLadoA("Tigre");
		estadoInicial.agregarLadoA("Burro");
		estadoInicial.agregarLadoA("Paja");

		int identificador  = this.agregarVertice(estadoInicial);
		colaEstados.add(obtenerVertice(identificador));


		
		while (!colaEstados.isEmpty()) {
			Vertice<EstadoTigreBurroPaja> estado = colaEstados.remove();
			List<EstadoTigreBurroPaja> estadosNuevos = estados (estado.getContenido());
			int identificadorNuevo;

			for (EstadoTigreBurroPaja estadoNuevo: estadosNuevos) {

				//sí el estado conduce a una solución
				if (estadoNuevo.getTipoEstado() == 1) {
					if (estado.getContenido().getEstadoAnterior() == null) {
						identificadorNuevo = this.agregarVertice(estadoNuevo);		
						this.agregarArista(estado.getIdentificador(), identificadorNuevo, 1);
						colaEstados.add(obtenerVertice(identificadorNuevo));	
					}
					else {

						if(!estado.getContenido().getEstadoAnterior().igual(estadoNuevo)) {


							if (estado.getContenido().getLadoA().contains("Burro") 
									&& estado.getContenido().getLadoB().contains("Tigre")
									&& estado.getContenido().getLadoB().contains("Paja") && estado.getContenido().getLadoBarca() == 'B') {
								if ((estadoNuevo.getLadoA().contains("Paja") && estadoNuevo.getLadoA().contains("Burro")
										&& estadoNuevo.getEstadoAnterior().getLadoB().contains("Tigre"))||										
										
									(estadoNuevo.getLadoA().contains("Tigre") && estadoNuevo.getLadoA().contains("Burro")
										&& estadoNuevo.getEstadoAnterior().getLadoB().contains("Paja"))) {

								}
								else {

									identificadorNuevo = this.agregarVertice(estadoNuevo);		
									this.agregarArista(estado.getIdentificador(), identificadorNuevo, 1);
									colaEstados.add(obtenerVertice(identificadorNuevo));	
								}
							}
							else {			

								identificadorNuevo = this.agregarVertice(estadoNuevo);
								this.agregarArista(estado.getIdentificador(), identificadorNuevo, 1);
								colaEstados.add(obtenerVertice(identificadorNuevo));	
							}

						}
					}		
				}
				//sí el estado es una solución
				if (estadoNuevo.getTipoEstado() == 0) {
					identificadorNuevo = this.agregarVertice(estadoNuevo);
					this.agregarArista(estado.getIdentificador(), identificadorNuevo, 1);
					this.identificadoresSolucion.add(identificadorNuevo);

				}
				//sí el estado no conduce a una solución
				if (estadoNuevo.getTipoEstado() == -1) {
					identificadorNuevo = this.agregarVertice(estadoNuevo);
					this.agregarArista(estado.getIdentificador(), identificadorNuevo, 1);
				}

			}
		}

	}

	// Para un estado del problema genera todos los posibles estados haciendo un
	// movimiento
	public static List<EstadoTigreBurroPaja> estados(EstadoTigreBurroPaja estado) throws CloneNotSupportedException {
		List<EstadoTigreBurroPaja> estadosNuevos = new ArrayList<EstadoTigreBurroPaja>();

		if (estado.getLadoBarca() == 'A') {

			EstadoTigreBurroPaja estadoNuevo = new EstadoTigreBurroPaja('B');
			estadoNuevo = (EstadoTigreBurroPaja) estado.clone();
			estadoNuevo.setEstadoAnterior(estado);
			estadoNuevo.setLadoBarca('B');
			estadosNuevos.add(estadoNuevo);

			for (String elemento : estado.getLadoA()) {
				estadoNuevo = new EstadoTigreBurroPaja('B');
				estadoNuevo = (EstadoTigreBurroPaja) estado.clone();
				estadoNuevo.setEstadoAnterior(estado);
				estadoNuevo.moverElementoaB(elemento);
				estadosNuevos.add(estadoNuevo);

			}

		} else {

			EstadoTigreBurroPaja estadoNuevo = new EstadoTigreBurroPaja('A');
			estadoNuevo = (EstadoTigreBurroPaja) estado.clone();
			estadoNuevo.setEstadoAnterior(estado);
			estadoNuevo.setLadoBarca('A');
			estadosNuevos.add(estadoNuevo);

			for (String elemento : estado.getLadoB()) {

				estadoNuevo = new EstadoTigreBurroPaja('A');
				estadoNuevo = (EstadoTigreBurroPaja) estado.clone();
				estadoNuevo.setEstadoAnterior(estado);
				estadoNuevo.moverElementoaA(elemento);
				estadosNuevos.add(estadoNuevo);
			}
		}

		return estadosNuevos;
	}

}
