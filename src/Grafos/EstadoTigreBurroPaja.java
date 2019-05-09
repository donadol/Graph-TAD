package Grafos;

import java.util.ArrayList;
import java.util.List;

/*
 * Esta clase muestra un estado en el problema del tigre, el burro y la paja
 * Tiene dos listas:
 * ladoA donde están las cadenas con los elementos en el ladoA
 * ladoB donde están las cadenas con los elementos en el ladoB
 * ladoBarca = determina el lado en el que está ubicada la barca
 */
public class EstadoTigreBurroPaja implements Cloneable{
	
	private List<String> ladoA;
	private List<String> ladoB;
	private char ladoBarca;
	
	private EstadoTigreBurroPaja estadoAnterior;
	

	public EstadoTigreBurroPaja(char lado) {
		// TODO Auto-generated constructor stub
		ladoA = new ArrayList<String>();
		ladoB = new ArrayList<String>();	
		this.ladoBarca = lado;
	}


	public List<String> getLadoA() {
		return ladoA;
	}


	public void setLadoA(List<String> ladoA) {
		this.ladoA = ladoA;
	}


	public List<String> getLadoB() {
		return ladoB;
	}


	public void setLadoB(List<String> ladoB) {
		this.ladoB = ladoB;
	}


	
	
	public char getLadoBarca() {
		return ladoBarca;
	}


	public void setLadoBarca(char ladoBarca) {
		this.ladoBarca = ladoBarca;
	}


	public void agregarLadoA(String elemento) {
		ladoA.add(elemento);
	}
	public void agregarLadoB(String elemento) {
		ladoB.add(elemento);
	}
	
	
	
	
	public EstadoTigreBurroPaja getEstadoAnterior() {
		return estadoAnterior;
	}


	public void setEstadoAnterior(EstadoTigreBurroPaja estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}


	/*
	 * Determina el tipo de estado:
	 * -1 es inválido: 
	 * El Tigre y el Burro están en el mismo lado y la barca no está en el lado
	 * El Burro y la Paja están en el mismo lado y la barca no está en el lado
	 * 
	 * 0 si es una solución:
	 * El Tigre, el Burro, la Paja y el Barco están en el lado B
	 */
	public int getTipoEstado() {
		
		//Lado A
		if (ladoA.contains("Tigre") && ladoA.contains("Burro") && ladoBarca=='B')
			return -1;
		if (ladoA.contains("Burro") && ladoA.contains("Paja") && ladoBarca=='B')
			return -1;
		
		//Lado B
		if (ladoB.contains("Tigre") && ladoB.contains("Burro") && ladoBarca=='A')
			return -1;
		if (ladoB.contains("Burro") && ladoB.contains("Paja") && ladoBarca=='A')
			return -1;
		
		if (ladoB.contains("Burro") && ladoB.contains("Tigre") && ladoB.contains("Paja") && ladoBarca == 'B') {
			return 0;
		}
		
		return 1;
	}
	
	public String toString() {
		String estado = "";
		if (ladoBarca == 'A') 
			estado += "Barca";
		
		for (String elementoA: ladoA) 
			estado += " "+ elementoA;
		
		
		estado += " |▒▒▒▒▒| ";
		
		if (ladoBarca == 'B') 
			estado += "Barca";
		
		for (String elementoA: ladoB) 
			estado += " "+ elementoA;
		
		return estado;		
		
	}
	
	@Override
	protected EstadoTigreBurroPaja clone() throws CloneNotSupportedException {
		EstadoTigreBurroPaja clon = new EstadoTigreBurroPaja(this.ladoBarca);
		clon.setLadoA(new ArrayList<>(ladoA));
		clon.setLadoB(new ArrayList<>(ladoB));

		return clon;
	}
	
	public void moverElementoaA(String elemento) {
		
		ladoB.remove(elemento);
		ladoA.add(elemento);
		ladoBarca = 'A';
		
		
	}
	public void moverElementoaB(String elemento) {

		ladoA.remove(elemento);
		ladoB.add(elemento);
		ladoBarca = 'B';
		
	}
	
	public boolean igual (EstadoTigreBurroPaja o) {
		
		if (ladoA.containsAll( o.getLadoA() ) && ladoB.containsAll(o.getLadoB())&& ladoBarca == o.getLadoBarca() ) {
			return true;
		}

		
		return false;
	}
	
	

	
	
	

}
