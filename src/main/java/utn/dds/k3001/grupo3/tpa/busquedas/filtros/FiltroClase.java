package utn.dds.k3001.grupo3.tpa.busquedas.filtros;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public class FiltroClase implements FiltroPOI {
	String nombreClase;
	public FiltroClase(String nombreClase)
	{
		this.nombreClase=nombreClase;
	}
	@Override
	public boolean filtrar(POI elem) {
		return elem.getClass().getName().contains(nombreClase);
	}

}
