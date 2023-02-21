package caso12023;

public class Main {

	public static void main(String[] args) {
		
		
		int procesos = 1;
		
		
		int productos = 0;
		
		int tamañoBuffers = 0;
		int modulo = productos%procesos;
		int productosxproceso = productos/procesos;
		Buffer buzon, String color, int etapa,int productos
		Buffer b1 = new Buffer(tamañoBuffers);
		Buffer b2 = new Buffer(tamañoBuffers);
		Buffer bfinal = new Buffer(productos+1);
		
		
		for (int i=1;i<procesos;i++) {
			Proceso p = new Proceso(b1,"Azul",1,productosxproceso);
		}
		
		for (int i=1;i<procesos;i++) {
			
		}
		
		for (int i=1;i<procesos;i++) {
			
		}	

	}

}
