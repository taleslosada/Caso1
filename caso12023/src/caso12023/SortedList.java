package caso12023;

import java.util.ArrayList;

public class SortedList {
	
	
	ArrayList<Producto> lista = new ArrayList<Producto>();
	
	
	
	
	
	
	public void agregar(Producto nuevo) {
		if (lista.size()==0) {
			lista.add(nuevo);
			
		} else if (lista.size()==1) {
			if (nuevo.getId()<lista.get(0).getId()) {
				lista.add(0, nuevo);
			}
		} else {
			if (nuevo.getId()<lista.get(0).getId()) {
				lista.add(0, nuevo);
				return;
			}
			for (int i=1;i<lista.size()-1;i++) {
				int id = nuevo.getId();
				if (id>lista.get(i-1).getId() && id<lista.get(i).getId()) {
					lista.add(i, nuevo);
					return;
				}
			}
			lista.add(nuevo);
		}
		
	}
	
	public ArrayList<Producto> Listaorganizada(){
		return lista;
	}

	
	
}
