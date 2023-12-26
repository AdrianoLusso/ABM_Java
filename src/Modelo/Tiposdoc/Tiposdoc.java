package Modelo.Tiposdoc;

import java.util.LinkedList;

public class Tiposdoc {
	private static int[] id;
	private static String[] descripcion;
	
	public static void cargarTiposdoc(LinkedList<Integer> idList,LinkedList<String> descripcionList) {
		id = new int[idList.size()];
		descripcion = new String[descripcionList.size()];
		int total = idList.size();
				
		for (int i = 0;i<total;i++) {
			id[i] = idList.poll();
			descripcion[i] = descripcionList.poll();
		}
		
	}
	
	public static int length() {
		return id.length;
	}
	
	public static int obtenerId(int pos) {
		return id[pos];
	}
	
	public static String obtenerDescripcion(int pos) {
		return descripcion[pos];
	}
	
	public static String obtenerPorId(int idInput) {
		String resultado;
		int i = 0;
		while(i<id.length && id[i]!= idInput) {
			i++;
		}

		if(i<id.length) {
			resultado = descripcion[i];
		}else {
			resultado = "no encontrado";
		}
		
		return resultado;
	}
}
