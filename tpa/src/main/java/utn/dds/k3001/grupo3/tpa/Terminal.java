package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal implements ObserverBusqueda
{
	private String nombre;
	private Mapa mapa;
	private List<Busqueda> busquedas;
	public List<ObserverBusqueda> observersBusqueda;

	public Terminal(String nombre, Mapa mapa){
		this.nombre = nombre;
		this.mapa = mapa;
		this.busquedas = new LinkedList<Busqueda>();
		this.observersBusqueda = new LinkedList<ObserverBusqueda>();
		agregarObserverBusqueda(this);
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
		Busqueda busqueda = new Busqueda(resultado.size(), criterio, inicio, LocalTime.now(), fecha);
		observersBusqueda.stream().forEach(observer -> observer.seBusco(busqueda));
		return resultado;
	}
	public void seBusco(Busqueda busqueda){
		busquedas.add(busqueda);
	}

	public int busquedasEnFecha(LocalDate fecha){	
		return busquedas.stream().filter(Busqueda -> Busqueda.esEnFecha(fecha)).collect(Collectors.toList()).size();
	}

	public List<Integer> cantResultadosParcialesDeBusquedas(){
		return busquedas.stream().map(busqueda ->busqueda.getCantidadResultados()).collect(Collectors.toList());
	}

	public int cantResultadosTotalesDeBusquedas(){
		return this.cantResultadosParcialesDeBusquedas().stream().mapToInt(Integer::intValue).sum();
	}
	
	public List<Busqueda> getBusquedas(){
		return busquedas;
	}
	
	public int cantObserversBusqueda(){
		return observersBusqueda.stream().collect(Collectors.toList()).size();
	}
}
