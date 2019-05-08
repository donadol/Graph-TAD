package Grafos;

import java.util.ArrayList;
import java.util.List;


public class GrafoCambioDeMoneda<T> extends GrafoMatriz<T>{

	public GrafoCambioDeMoneda() {
		// TODO Auto-generated constructor stub
	}
	
	public static GrafoCambioDeMoneda CambioDeMoneda(GrafoCambioDeMoneda graph, Vertice inicial) {
		inicial.setDistancia(0);

		List<Vertice> nodosAgregados = new ArrayList<>();
		List<Vertice> nodosNoAgregados = new ArrayList<>();

		nodosNoAgregados.add(inicial);

		while (nodosNoAgregados.size() != 0) {
			Vertice verticeActual = getMenorDistanciaNodoCambioDeMoneda(nodosNoAgregados);
			nodosNoAgregados.remove(verticeActual);
			for (Vertice conexionActual : verticeActual.getVecinos()) {
				int conexionPeso = conexionActual.getIdentificador();
				if (!nodosAgregados.contains(conexionActual)) {
					MinimaDistanciaCambioDeMoneda(conexionActual, conexionPeso, verticeActual);
					if (!nodosNoAgregados.contains(conexionActual)) {
						nodosNoAgregados.add(conexionActual);
					}
				}
			}
			nodosAgregados.add(verticeActual);
		}
		return graph;
	}

	private static Vertice getMenorDistanciaNodoCambioDeMoneda(List<Vertice> nodosNoAgregados) {
		Vertice menorDistanciaNodo = null;
		int menorDistancia = Integer.MAX_VALUE;
		for (Vertice nodo : nodosNoAgregados) {
			int distanciaNodo = nodo.getDistancia();
			if (distanciaNodo < menorDistancia) {
				menorDistancia = distanciaNodo;
				menorDistanciaNodo = nodo;
			}
		}
		return menorDistanciaNodo;
	}

	private static void MinimaDistanciaCambioDeMoneda(Vertice nodoAEvaluar, int peso, Vertice verticeActual) {
		int nodoActualDistancia = verticeActual.getDistancia();
		if (nodoActualDistancia + peso < nodoAEvaluar.getDistancia()) {
			nodoAEvaluar.setDistancia(nodoActualDistancia + peso);
			List<Vertice> menorDistancia = verticeActual.getMenorDistancia();
			menorDistancia.add(verticeActual);
			nodoAEvaluar.setMenorDistancia(menorDistancia);
		}
	}


}
