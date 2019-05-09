package Grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PruebaPuntoUNO{

	public PruebaPuntoUNO() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws CloneNotSupportedException, LimiteException {
		// TODO Auto-generated method stub
		
		GrafoTigreBurroPaja grafoTigreBurroPaja = new GrafoTigreBurroPaja();
		Map<Integer, Vertice<EstadoTigreBurroPaja>> caminosMasCortos = grafoTigreBurroPaja.dijkstra(0);
		

		
		System.out.println("SOLUCIÓN 1: \n");
		construirCaminoDijkstra(grafoTigreBurroPaja, caminosMasCortos, grafoTigreBurroPaja.getIdentificadoresSolucion().get(0));
		System.out.println("\n\n\nSOLUCIÓN 2: \n");
		construirCaminoDijkstra(grafoTigreBurroPaja, caminosMasCortos, grafoTigreBurroPaja.getIdentificadoresSolucion().get(1));
			

	}
	
	//permite imprimir el camino más corto dado por Dijkstra a través del identificador de un vertice
	private static void construirCaminoDijkstra(GrafoTigreBurroPaja grafoTigreBurroPaja, Map<Integer, Vertice<EstadoTigreBurroPaja>> previo, int identificador) {
		boolean caminoTerminado = false;
		List<EstadoTigreBurroPaja> caminoAlReves = new ArrayList<EstadoTigreBurroPaja>();
		caminoAlReves.add(grafoTigreBurroPaja.obtenerVertice(identificador).getContenido());
			
		
		while (!caminoTerminado) {
			if (previo.get(identificador)!= null) {
				caminoAlReves.add(previo.get(identificador).getContenido());
				identificador = previo.get(identificador).getIdentificador();
			}
			else {
				caminoTerminado = true;
			}
				
		}
		
		Collections.reverse(caminoAlReves);
		
		for (EstadoTigreBurroPaja estado: caminoAlReves) {
			System.out.println(estado);
		}
	}
	


}
