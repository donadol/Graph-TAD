package CambioMoneda;

import java.util.ArrayList;
import java.util.List;

import GrafoMatriz.GrafoMatriz;
import Grafos.Vertice;

public class GrafoCambioDeMoneda extends GrafoMatriz<Integer>{

	public GrafoCambioDeMoneda() {}
	
	public GrafoCambioDeMoneda CambioDeMoneda(GrafoCambioDeMoneda graph, Vertice<Integer> inicial) {
		inicial.setDistancia(0);

		List<Vertice<Integer>> nodosAgregados = new ArrayList<>();
		List<Vertice<Integer>> nodosNoAgregados = new ArrayList<>();
		List<Vertice<Integer>> listaAuxiliar = new ArrayList<>();

		nodosNoAgregados.add(inicial);

		while (nodosNoAgregados.size() != 0) {
			Vertice<Integer> verticeActual = getMenorDistanciaNodoCambioDeMoneda(nodosNoAgregados);
			nodosNoAgregados.remove(verticeActual);
			listaAuxiliar = graph.obtenerVecinos(verticeActual);
			for(int i = 0; i < listaAuxiliar.size(); i++)
			{
				int conexionPeso = listaAuxiliar.get(i).getIdentificador();
				if (!nodosAgregados.contains(listaAuxiliar.get(i))) {
					MinimaDistanciaCambioDeMoneda(listaAuxiliar.get(i), conexionPeso, verticeActual);
					if (!nodosNoAgregados.contains(listaAuxiliar.get(i))) {
						nodosNoAgregados.add(listaAuxiliar.get(i));
					}
				}
			}
			nodosAgregados.add(verticeActual);
		}
		return graph;
	}

	private Vertice<Integer> getMenorDistanciaNodoCambioDeMoneda(List<Vertice<Integer>> nodosNoAgregados) {
		Vertice<Integer> menorDistanciaNodo = null;
		int menorDistancia = 99999999;
		for (Vertice<Integer> nodo : nodosNoAgregados) {
			int distanciaNodo = nodo.getDistancia();
			if (distanciaNodo < menorDistancia) {
				menorDistancia = distanciaNodo;
				menorDistanciaNodo = nodo;
			}
		}
		return menorDistanciaNodo;
	}

	private void MinimaDistanciaCambioDeMoneda(Vertice<Integer> nodoAEvaluar, int peso, Vertice<Integer> verticeActual) {
		int nodoActualDistancia = verticeActual.getDistancia();
		if (nodoActualDistancia + peso < nodoAEvaluar.getDistancia()) {
			nodoAEvaluar.setDistancia(nodoActualDistancia + peso);
			List<Vertice<Integer>> menorDistancia = verticeActual.getMenorDistancia();
			menorDistancia.add(verticeActual);
			nodoAEvaluar.setMenorDistancia(menorDistancia);
		}
	}


}
