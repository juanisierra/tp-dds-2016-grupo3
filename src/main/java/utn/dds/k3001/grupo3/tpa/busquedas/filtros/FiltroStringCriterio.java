package utn.dds.k3001.grupo3.tpa.busquedas.filtros;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public class FiltroStringCriterio implements FiltroPOI {
	String criterio;
	public FiltroStringCriterio(String criterio)
	{
		this.criterio = criterio;
	}
	@Override
	public boolean filtrar(POI elem) {
		return elem.esBuscado(criterio);
	}

}
