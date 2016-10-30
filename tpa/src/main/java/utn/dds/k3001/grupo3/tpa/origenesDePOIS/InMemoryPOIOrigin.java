package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.LinkedList;
import java.util.List;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public class InMemoryPOIOrigin implements POIOrigin {
	LinkedList<POI> listaPOIS;
	
	public InMemoryPOIOrigin() {
		listaPOIS= new LinkedList<POI>();
	}
	
	@Override
	public List<POI> getPOIS() {
		return listaPOIS;
	}

	@Override
	public void eliminarPOI(POI poi) {
		listaPOIS.remove(poi);
	}

	@Override
	public void vaciar() {
		listaPOIS= new LinkedList<POI>();
	}

	@Override
	public void agregarPOI(POI poi) {
		listaPOIS.add(poi);
	}
	
	@Override
	public void eliminarPorNumero(Long id) {
		listaPOIS.removeIf(POI -> Long.valueOf(POI.getID()).equals(id));
		
	}
}
