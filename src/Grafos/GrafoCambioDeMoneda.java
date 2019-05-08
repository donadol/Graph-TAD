package Grafos;

import java.util.ArrayList;
import java.util.List;

public class GrafoCambioDeMoneda<T> extends GrafoListaVecinos<T>{

	public GrafoCambioDeMoneda() {
		// TODO Auto-generated constructor stub
	}
	
	public static GrafoCambioDeMoneda cambioDeMoneda(GrafoCambioDeMoneda graph, Vertice inicial) {
		inicial.setDistancia(0);

		List<Vertice> verticesAgregados = new ArrayList<>();
		List<Vertice> verticesNoAgregados = new ArrayList<>();


		verticesNoAgregados.add(inicial);

		while (verticesNoAgregados.size() != 0) {
			Vertice NodoActual = getMenorDistanciaNodoCambioDeMoneda(verticesNoAgregados);
			verticesNoAgregados.remove(NodoActual);
			//Corregir para buscar forma de corregir el ciclo
			for (Vertice conexionActual : NodoActual.) {
				int conexionPeso = Integer.parseInt((String) conexionActual.getContenido());
				if (!verticesAgregados.contains(conexionActual)) {
					MinimaDistanciaCambioDeMoneda(conexionActual, conexionPeso, NodoActual);
					if (!verticesNoAgregados.contains(conexionActual)) {
						verticesNoAgregados.add(conexionActual);
					}
				}
			}
			verticesAgregados.add(NodoActual);
			System.out.println("Nombre " + NodoActual.getContenido());
		}
		return graph;
	}


	private static Vertice getMenorDistanciaNodoCambioDeMoneda(List<Vertice> verticesNoAgregados) {
		Vertice menorDistanciaVertice = null;
		Double menorDistancia = Double.MAX_VALUE;
		for (Vertice vertice : verticesNoAgregados) {
			Double distanciaVertice = (double) vertice.getDistancia();
			if (distanciaVertice < menorDistancia) {
				menorDistancia = distanciaVertice;
				menorDistanciaVertice = vertice;
			}
		}
		return menorDistanciaVertice;
	}

	private static void MinimaDistanciaCambioDeMoneda(Vertice verticeAEvaluar, int peso, Vertice verticeActual) {
		int verticeActualDistancia = verticeActual.getDistancia();
		if (verticeActualDistancia + peso < verticeAEvaluar.getDistancia()) {
			verticeAEvaluar.setDistancia(verticeActualDistancia + peso);
			List<Vertice> menorDistancia = verticeActual.getMenorDistancia();
			menorDistancia.add(verticeActual);
			verticeAEvaluar.setMenorDistancia(menorDistancia);
		}
	}

}


