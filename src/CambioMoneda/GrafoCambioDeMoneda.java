package CambioMoneda;

import java.util.ArrayList;
import java.util.List;

import GrafoListaVecinos.GrafoListaVecinos;
import GrafoMatriz.GrafoMatriz;
import Grafos.Vertice;

public class GrafoCambioDeMoneda extends GrafoListaVecinos<String>{

	public GrafoCambioDeMoneda() {}
	
	public GrafoCambioDeMoneda CambioDeMoneda(GrafoCambioDeMoneda graph, Vertice<String> inicial) {
		inicial.setDistancia(0);

		List<Vertice<String>> nodosAgregados = new ArrayList<>();
		List<Vertice<String>> nodosNoAgregados = new ArrayList<>();
		List<Vertice<String>> listaAuxiliar = new ArrayList<>();

		nodosNoAgregados.add(inicial);

		while (nodosNoAgregados.size() != 0) {
			Vertice<String> verticeActual = getMenorDistanciaNodoCambioDeMoneda(nodosNoAgregados);
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
			System.out.println("Moneda " + verticeActual.getContenido());
		}
		return graph;
	}

	private Vertice<String> getMenorDistanciaNodoCambioDeMoneda(List<Vertice<String>> nodosNoAgregados) {
		Vertice<String> menorDistanciaNodo = null;
		int menorDistancia = 99999999;
		for (Vertice<String> nodo : nodosNoAgregados) {
			int distanciaNodo = nodo.getDistancia();
			if (distanciaNodo < menorDistancia) {
				menorDistancia = distanciaNodo;
				menorDistanciaNodo = nodo;
			}
		}
		return menorDistanciaNodo;
	}

	private void MinimaDistanciaCambioDeMoneda(Vertice<String> nodoAEvaluar, int peso, Vertice<String> verticeActual) {
		int nodoActualDistancia = verticeActual.getDistancia();
		if (nodoActualDistancia + peso < nodoAEvaluar.getDistancia()) {
			nodoAEvaluar.setDistancia(nodoActualDistancia + peso);
			List<Vertice<String>> menorDistancia = verticeActual.getMenorDistancia();
			menorDistancia.add(verticeActual);
			nodoAEvaluar.setMenorDistancia(menorDistancia);
		}
	}


}
