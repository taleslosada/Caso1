package caso12023;

import java.util.Stack;

public class Buffer {
	
	private Stack<Producto> buff ;
	private int tamaño;
	public Buffer (int tamaño) {
	this.tamaño = tamaño ;
	buff = new Stack<Producto> () ;
	}
	
	public synchronized void mandarPasivo (Producto i) {
		
		while (buff.size()==tamaño) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buff.push(i);
		notifyAll();
	}
	
	
	
	public synchronized Producto retirarPasivo () {
		
		while (buff.size()==0  || !buff.peek().getTipo().equals("Azul")) {
			
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Producto p = buff.pop();
		notifyAll();
		return p;
	}

	public Producto retirarSemiactivo() {
		Producto i = null;
		while (i==null) {
			Thread.yield();
			i = retiroSemi();
		}
		
		notifyAll();
		return i;
		
		
	}
	
public void mandarSemiactivo(Producto i) {
		
		while (mandarSemiactivoAUX(i)) {
			Thread.yield();
		}
		
		notifyAll();
	}
	
	public synchronized boolean mandarSemiactivoAUX(Producto i) {
		if (buff.size()==tamaño) {
			return true;
		
		} else {
			buff.push(i);
			return false;
		}
	}
	public synchronized Producto retiroSemi() {
		
		if (buff.size()==0  || buff.peek().getTipo().equals("Azul")) {
			notifyAll();
			return null;
		} else {
			return buff.pop();
		}
	}

	
	public Producto retirarActivo() {
		
		while (buff.size()==0) {
		}
		return buff.pop();
	}
}
