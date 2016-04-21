package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa 
{
	private List<POI> POIS;
	
	public Mapa()
	{
		POIS = new LinkedList<POI>();
	}
	public void agregarPoi(POI poiNvo)
	{
		POIS.add(poiNvo);
	}
	public List<POI> buscar(String criterio)
	{
		return POIS.stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList());
	}
}
