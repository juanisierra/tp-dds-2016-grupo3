package utn.dds.k3001.grupo3.tpa;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
		return origenesDeDatos.stream()
							  .flatMap(origen -> origen.buscar(criterio).stream())
							  .collect(Collectors.toList());
	}
}
