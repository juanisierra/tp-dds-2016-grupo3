package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.List;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public interface OrigenDeDatos {
	public List<POI> buscar(String criterio);
}
