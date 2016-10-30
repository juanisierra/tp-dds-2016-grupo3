package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.List;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public interface POIOrigin {
	
	public List<POI> getPOIS();
	
	public void eliminarPOI(POI poi);
	
	public void vaciar();

	public void agregarPOI(POI poi);
	
	public void eliminarPorNumero(Long id);
}
