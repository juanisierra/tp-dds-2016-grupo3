package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa 
{
	private List<POI> ListaPOIS;
	
	public Mapa(){
		ListaPOIS = new LinkedList<POI>();
	}
	
	public void agregarPoi(POI poiNvo){
		ListaPOIS.add(poiNvo);
	}
	public void eliminarPoi(POI poiAEliminar){
		ListaPOIS.remove(poiAEliminar);
	}
	public List<POI> verTodosLosPois(){
		return ListaPOIS;
	}
	public List<POI> buscar(String criterio){
		return ListaPOIS.stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList());
	}
}
