package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal
{
	private String nombre;
	private Mapa mapa;
	private List<ObserverBusqueda> observersBusqueda;

	public Terminal(String nombre, Mapa mapa){
		this.nombre = nombre;
		this.mapa = mapa;
		this.observersBusqueda = new LinkedList<ObserverBusqueda>();
	}
	public void agregarObserverBusqueda(ObserverBusqueda observerBusqueda){
		observersBusqueda.add(observerBusqueda);
	}
	
	public void eliminarObserverBusqueda(ObserverBusqueda observerBusqueda){
		observersBusqueda.remove(observerBusqueda);
	}
	
	public List<POI> buscar(String criterio){
		LocalTime inicio = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		List<POI> resultado = mapa.buscar(criterio);
		Busqueda busqueda = new Busqueda(this,resultado.size(), criterio, inicio, LocalTime.now(), fecha);
		observersBusqueda.stream().forEach(observer -> observer.agregar(busqueda));
		return resultado;
	}
	
	public int cantObserversBusqueda(){
		return observersBusqueda.size();
	}
}
