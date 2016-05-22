package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Mapa 
{
	private List<OrigenDeDatos> origenesDeDatos;
	private BusquedasManager busquedasManager;
	
	public Mapa(){
		origenesDeDatos = new LinkedList<OrigenDeDatos>();
		busquedasManager = new BusquedasManager();
	}
	public void agregarOrigenDeDatos(OrigenDeDatos origen){
		origenesDeDatos.add(origen);
	}
	public List<Busqueda> busquedasEnFecha(LocalDate fecha)
	{
		return busquedasManager.busquedasEnFecha(fecha);
	}
	public List<POI> buscar(String criterio){
		LocalTime inicio = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		LinkedList<POI> listaABuscar = new LinkedList<POI>();
		origenesDeDatos.forEach(origen -> listaABuscar.addAll(origen.buscar(criterio)));
		busquedasManager.agregar(new Busqueda(listaABuscar.size(), criterio, inicio, LocalTime.now(), fecha));
		return listaABuscar;
	}
}
