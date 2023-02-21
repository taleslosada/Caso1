package caso12023;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Proceso extends Thread{
	
	

	

	private Buffer buzon;
	
	private String color;
	
	private int etapa;
	
	private int productos;
	
	private static IdGen generador = new IdGen();
	
	private CyclicBarrier produccion;
	
	public Proceso(Buffer buzon, String color, int etapa,int productos) {
		super();
		this.buzon = buzon;
		this.color = color;
		this.etapa = etapa;
		this.productos = productos;
	}
	
	public Proceso(Buffer buzon, String color, int etapa, int productos, CyclicBarrier produccion) {
		super();
		this.buzon = buzon;
		this.color = color;
		this.etapa = etapa;
		this.productos = productos;
		this.produccion = produccion;
	}
	
	
	
	public void run() {
		//EL THREAD HACE LO QUE TIENE QUE HACER SEGUN SU ETAPA
		switch (etapa) {
		case 1:;
			//CREAMOS LOS PRODUCTOS
			while (productos>0) {
				int id = generador.asignar();
				String mensaje = id + ". Producto de color " + color; 
				Producto nuevo = new Producto(id,mensaje);
				enviar(nuevo);
				System.out.println("||ETAPA 1||\nEl producto " + id + " ha sido generado");
				productos--;	
			}
		break;
		case 2:
			//PROCESAMOS LOS PRODUCTOS
			while (productos>0) {
				Producto producto = recoger();
				Random random = new Random();
				int lapso = random.nextInt(450)+50;
				System.out.println("Procesando el producto " + producto.getId() 
				+ " en un lapso de " + lapso + " ms");
				producto.setMensaje(producto.getMensaje()+"");
				//Simulamos el tiempo de procesamiento del producto
				try {
					Thread.sleep(lapso);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("||ETAPA 2||\nEl producto " + producto.getId()+ " ha sido procesado\n\n");
				//Enviamos el producto al buzon
				enviar(producto);
				productos--;
			}
		break;
		case 3:
			while (productos>0) {
				//PROCESAMOS LOS PRODUCTOS
				Producto producto = recoger();
				Random random = new Random();
				int lapso = random.nextInt(450)+50;
				System.out.println("Procesando el producto " + producto.getId() 
				+ " en un lapso de " + lapso + " ms");
				producto.setMensaje(producto.getMensaje()+"");
				//Simulamos el tiempo de procesamiento del producto
				try {
					Thread.sleep(lapso);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("||ETAPA 3||\nEl producto " + producto.getId()+ " ha sido procesado\n\n");
				//Enviamos el producto al buzon
				enviar(producto);
				productos--;
			
			}
		break;
		case 4:
			SortedList organizador= new SortedList();
			while (productos>0) {
				//Recibimos y organizamos los productos
				Producto agregado = recoger();
				organizador.agregar(agregado);
			}
			ArrayList<Producto> productosOrganizados = organizador.Listaorganizada();
			//Con la lista de todos los productos organizados ya podemos empezar a imprimir
			System.out.println("|||PROCESO ROJO|||\nImprimiendo mensajes de productos generados:");
			for (int i =0;i<productosOrganizados.size();i++) {
				Producto prod = productosOrganizados.get(i);
				System.out.println(prod.getMensaje());
			}
			System.out.println("\n|||Todos los productos imprimidos|||\n Apagando thread...");
		break;
		}
		
		//Paramos en la barrera para indicar el fin de procesamiento de todos los threads de una etapa
		try {
			produccion.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	
	public Producto recoger() {
		if (color.equals("Naranja")) {
			//Si el proceso es de color naranja recoge los mensajes de forma semiactiva
			return buzon.retirarSemiactivo();
		} else if (color.equals("Azul")) {
			//Si el proceso es de color azul recoge los mensajes de forma pasiva
			return buzon.retirarPasivo();
		} else {
			//Si el proceso es de color rojo recoge los mensajes de forma activa
			return buzon.retirarActivo();
		}
	}
	
	public void enviar(Producto producto) {
		if (color.equals("Naranja")) {
			buzon.mandarSemiactivo(producto);
		} else {
			buzon.mandarPasivo(producto);
		}
	}

}
