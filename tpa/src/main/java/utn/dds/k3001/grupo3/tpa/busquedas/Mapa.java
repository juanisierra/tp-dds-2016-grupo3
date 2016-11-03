package utn.dds.k3001.grupo3.tpa.busquedas;

import java.io.IOException;
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
		RepositorioInterno.reset();
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
							  .flatMap(origen -> {
								try {
									return origen.buscar(criterio).stream();
								} catch (ClassNotFoundException | IOException e) {
									
									e.printStackTrace();
								}
								return new LinkedList<POI>().stream();
							})
							  .collect(Collectors.toList());
	}
	public List<POI> buscar(String criterio,String clase){
		return origenesDeDatos.stream()
							  .flatMap(origen -> {
								try {
									return origen.buscar(criterio).stream();
								} catch (ClassNotFoundException | IOException e) {
									
									e.printStackTrace();
								}
								return new LinkedList<POI>().stream();
							}).filter(elem -> elem.getClass().getName().contains(clase))
							  .collect(Collectors.toList());
	}
}
