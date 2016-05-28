package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;

public class Mapa 
{
	private List<OrigenDeDatos> origenesDeDatos;
	private RepositorioInterno repositorioInterno;
	
	public Mapa(){
		this.origenesDeDatos = new LinkedList<OrigenDeDatos>();
		this.repositorioInterno = new RepositorioInterno();
		this.origenesDeDatos.add(repositorioInterno);
	}
	public void agregarOrigenDeDatos(OrigenDeDatos origen){
		origenesDeDatos.add(origen);
	}
	public void agregarPoi(POI poiNvo){
		repositorioInterno.agregarPoi(poiNvo);
	}
	public void eliminarPoi(POI poiAEliminar){
		repositorioInterno.eliminarPoi(poiAEliminar);
	}
	public List<POI> buscar(String criterio){
		LinkedList<POI> poisBuscados = new LinkedList<POI>();
		origenesDeDatos.forEach(origen -> poisBuscados.addAll(origen.buscar(criterio)));
		return poisBuscados;
	}
}
