package Grafos;

import java.util.ArrayList;
import java.util.List;

public abstract class GrafoAristas<T> extends Grafo<T>{

	
	public GrafoAristas() 
	{
		
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
