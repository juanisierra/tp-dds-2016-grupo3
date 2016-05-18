package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapa 
{
	private List<POI> ListaPOIS;
	private List<OrigenDeDatos> origenesDeDatos;
	
	public Mapa(){
		ListaPOIS = new LinkedList<POI>();
		origenesDeDatos = new LinkedList<OrigenDeDatos>();
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
	public void agregarOrigenDeDatos(OrigenDeDatos origen){
		origenesDeDatos.add(origen);
	}
	public List<POI> buscar(String criterio){
		LinkedList<POI> listaABuscar = new LinkedList<POI>();
		listaABuscar.addAll(ListaPOIS.stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList()));
		origenesDeDatos.forEach(origen -> listaABuscar.addAll(origen.buscar(criterio)));
		return listaABuscar;
	}
}
