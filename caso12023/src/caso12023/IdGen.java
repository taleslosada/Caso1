package caso12023;

public class IdGen {
	
	private int cont = 0;
	
	
	public synchronized int asignar() {
		cont += 1;
		return cont;
	}

}

