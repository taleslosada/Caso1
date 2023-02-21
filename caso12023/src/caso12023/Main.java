package caso12023;

public class Main {

	public static void main(String[] args) {
		
		
		int procesos = 1;
		
		int productos = 0;
		
		int tamañoBuffers = 0;
		int modulo = productos%procesos;
		int productosxproceso = productos/procesos;
		
		Buffer b1 = new Buffer(tamañoBuffers);
		Buffer b2 = new Buffer(tamañoBuffers);
		Buffer bfinal = new Buffer(productos+1);
		
		
		for (int i=1;i<5;i++) {
			
			if (i==1) {
				if (modulo>0) {
					for (int k=0;k<modulo;k++) {
						Proceso p = new Proceso("Azul",i,productosxproceso+1,b1);p.run();
					}
				}
				for (int j=1;j<procesos-modulo;j++) {
					Proceso p = new Proceso("Azul",i,productosxproceso,b1);p.run();
				}
				Proceso n1 = new Proceso("Naranja",i,productosxproceso,b1);n1.run();
			} else if (i==2) {
				if (modulo>0) {
					for (int k=0;k<modulo;k++) {
						Proceso p = new Proceso(b1,"Azul",i,productosxproceso+1,b2);p.run();
					}
				}
				for (int j=1;j<procesos-modulo;j++) {
					Proceso p = new Proceso(b1,"Azul",i,productosxproceso,b2);p.run();
				}
				Proceso n1 = new Proceso(b1,"Naranja",i,productosxproceso,b2);n1.run();	
			} else if (i==3) {
				if (modulo>0) {
					for (int k=0;k<modulo;k++) {
						Proceso p = new Proceso(b2,"Azul",i,productosxproceso+1,bfinal);p.run();
					}
				}
				for (int j=1;j<procesos-modulo;j++) {
					Proceso p = new Proceso(b2,"Azul",i,productosxproceso,bfinal);p.run();
				}
				Proceso n1 = new Proceso(b2,"Naranja",i,productosxproceso,bfinal);n1.run();	
			}
		}		
		Proceso rojo = new Proceso(bfinal,"Rojo",4,productos);rojo.run();
		
		System.out.println("=============PROGRAMA FINALIZADO============");

	}
	
	
	

}
