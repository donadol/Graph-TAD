package Grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrafoReal<T> extends Grafo<T>{

	public GrafoReal() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public boolean agregarArista (int origen, int destino, int costo) {

		if(vertices.containsKey(origen) && vertices.containsKey(destino)) {
			Arista<T> aristaNueva = new Arista <T> (vertices.get(origen), vertices.get(destino), costo);
			((VerticeVecinos<T>) vertices.get(origen)).agregarVecino(aristaNueva);
			return true;
		}
		return false;
	}

		@Override
		public boolean eliminarArista (int origen, int destino) {
			
			if(vertices.containsKey(origen) && vertices.containsKey(destino)) {
				return ((VerticeVecinos<T>) vertices.get(origen)).eliminarVecino(destino);
			}
			
			return false;
		}


		@Override
		public int agregarVertice(T contenido, int i) {
			
			int idAntiguo = NumeroVertices;
			VerticeVecinos<T> verticeNuevo = new VerticeVecinos<T>();
			if(vertices.put(NumeroVertices, verticeNuevo) == null) {
				NumeroVertices++;
				return idAntiguo;
			}
			return -1;	
		}

		@Override
		public int eliminarVertice(int identificador) {
			if(vertices.remove(identificador) != null) 
				return identificador;
			else
				return -1;
		}


		@Override
		public List<Vertice<T>> obtenerVecinos (int identificador){
			
			ArrayList <Vertice<T>> listaSalida = new ArrayList<Vertice<T>>();
			for(Arista<T> a : ((VerticeVecinos<T>) vertices.get(identificador)).getVecinos()) {
				listaSalida.add(a.getDestino());
			}
			return listaSalida;
		}

		public List<Vertice<T>> obtenerVecinos (Vertice <T> vertice){
			
			ArrayList <Vertice<T>> listaSalida = new ArrayList<Vertice<T>>();
			
			for(Map.Entry<Integer, Vertice<T>> e : vertices.entrySet()) {
				for(Arista<T> a : ((VerticeVecinos<T>) vertices.get(vertice)).getVecinos()) {
					listaSalida.add(a.getDestino());
				}
			}
			return listaSalida;
		}


		@Override
		public List<Arista<T>> obtenerAristas()
		{
			List<Arista<T>> aristasaux = new ArrayList<Arista<T>>();
			for (int i = 0; i < vertices.size(); i++)
			{
				aristasaux.add(aristas.get(i));
			}
			return aristasaux;
		}


		@Override
		public int obtenerCostoArista(int origen, int destino) throws LimiteException {
			int costo=0;
			if(origen>= vertices.size() || destino>= vertices.size())
	            throw new LimiteException("Te pasaste");
			for(int i=0;i<aristas.size();i++)
		       {
		    	   
		    		  if( aristas.get(i).getOrigen().getIdentificador()==origen) {
		    			  for(int j=0;j<aristas.size();j++) {
		    				  if(aristas.get(j).getDestino().getIdentificador()==destino) {
		    					  costo=aristas.get(i).getCosto();
		    					  
		    				  }
		    			  }
		    		  }
		    	   
		       }
			return costo;
			
		}


		@Override
		public int obtenerCostoArista (Vertice<T> origen, Vertice<T> destino) throws LimiteException
		{
			int costo=0;
			if(origen.getIdentificador()>= vertices.size() || destino.getIdentificador()>= vertices.size())
	            throw new LimiteException("Te pasaste");
			for(int i=0;i<aristas.size();i++)
		       {
		    	   
		    		  if( aristas.get(i).getOrigen()==origen) {
		    			  for(int j=0;j<aristas.size();j++) {
		    				  if(aristas.get(j).getDestino()==destino) {
		    					  costo=aristas.get(i).getCosto();
		    					  
		    				  }
		    			  }
		    		  }
		    	   
		       }
			return costo;
			
		}








	}
