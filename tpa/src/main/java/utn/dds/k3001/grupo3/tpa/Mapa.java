package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;

public class Mapa 
{
	private List<OrigenDeDatos> origenesDeDatos;
	
	public Mapa(){
		origenesDeDatos = new LinkedList<OrigenDeDatos>();
	}
	public void agregarOrigenDeDatos(OrigenDeDatos origen){
		origenesDeDatos.add(origen);
	}
	public List<POI> buscar(String criterio){
		LinkedList<POI> listaABuscar = new LinkedList<POI>();
		origenesDeDatos.forEach(origen -> listaABuscar.addAll(origen.buscar(criterio)));
		return listaABuscar;
	}
}
