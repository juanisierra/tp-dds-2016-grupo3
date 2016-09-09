package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import utn.dds.k3001.grupo3.tpa.origenesDePOIS.OrigenDeDatos;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class Mapa 
{
	private List<OrigenDeDatos> origenesDeDatos;
	private final static Mapa INSTANCE = new Mapa();
	
	private Mapa(){
		this.origenesDeDatos = new LinkedList<OrigenDeDatos>();
		this.origenesDeDatos.add(RepositorioInterno.getInstance());
	}
	public  void resetMapa() {
		this.origenesDeDatos.clear();
		this.origenesDeDatos.add(RepositorioInterno.getInstance());
		RepositorioInterno.getInstance().resetRepositorio();
	}
	public static Mapa getInstance() {
		return INSTANCE;
	}
	public void agregarOrigenDeDatos(OrigenDeDatos origen){
		origenesDeDatos.add(origen);
	}
	public void agregarPoi(POI poiNvo){
		RepositorioInterno.getInstance().agregarPoi(poiNvo);
	}
	public void eliminarPoi(POI poiAEliminar){
		RepositorioInterno.getInstance().eliminarPoi(poiAEliminar);
	}
	public List<POI> buscar(String criterio){
		return origenesDeDatos.stream()
							  .flatMap(origen -> origen.buscar(criterio).stream())
							  .collect(Collectors.toList());
	}
	
}
