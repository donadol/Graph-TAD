package Grafos;

public class Nodo<T> {
	private int Pid;
	private Vertice<T> v;
	
	public Nodo(int pid, Vertice<T> v) {
		super();
		Pid = pid;
		this.v = v;
	}
	
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public Vertice<T> getV() {
		return v;
	}
	public void setV(Vertice<T> v) {
		this.v = v;
	}
}
