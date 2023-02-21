package caso12023;

public class Main {


    private static String input(String message) {
        try {
            System.out.print(message);
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            return lector.readLine();
        } catch (IOException e) {
            System.out.println("Error de lectura en la consola.");
            e.printStackTrace();
            return null;
        }
    }

	public static void main(String[] args) {
		
		
		int procesos = 1;
		
		int productos = 0;
		
		int tama�oBuffers = 0;
		int modulo = productos%procesos;
		int productosxproceso = productos/procesos;
		
		Buffer b1 = new Buffer(tama�oBuffers);
		Buffer b2 = new Buffer(tama�oBuffers);
		Buffer bfinal = new Buffer(productos+1);

		Integer procesos = Integer.parseInt(input("\nIngrese el n�mero total de procesos deseados.\n> "));
        System.out.println("Habr� 1 proceso naranja y " + (procesos-1) + " procesos azules.\n");

        productosxproceso = Integer.parseInt(input("Ingrese la cantidad de productos por proceso a crear.\n> "));

        tama�oBuffers = Integer.parseInt(input("\nIngrese el tama�o de los primeros 2 buffers.\n> "));
        System.out.println("Los buffers 1 y 2 tendr�n tama�o de " + tama�oBuffers + "; el 3 ser� ilimitado.\n")
		
		
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
