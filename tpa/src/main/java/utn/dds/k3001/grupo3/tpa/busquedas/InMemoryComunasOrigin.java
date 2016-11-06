package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.LinkedList;
import java.util.List;

import utn.dds.k3001.grupo3.tpa.pois.Comuna;


public class InMemoryComunasOrigin implements ComunasOrigin{
	private List<Comuna> listaComunas;
	
	public InMemoryComunasOrigin() {
		listaComunas = new LinkedList<Comuna>();
	}
	@Override
	public void agregarComuna(Comuna comuna) {
		listaComunas.add(comuna);
	}

	@Override
	public List<Comuna> obtenerComunas() {
		return listaComunas;
	}
	@Override
	public void reset() {
		listaComunas = new LinkedList<Comuna>();
	}

}
