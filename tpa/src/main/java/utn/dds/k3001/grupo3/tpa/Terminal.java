package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Terminal
{
	private String nombre;
	private List<Busqueda> busquedas;
	private Mapa mapa;
	private List<ObserverBusqueda> observersBusqueda;
	
	public Terminal(String nombre, Mapa mapa)
	{
		this.nombre = nombre;
		this.mapa = mapa;
		this.busquedas = new LinkedList<Busqueda>();
		this.observersBusqueda = new LinkedList<ObserverBusqueda>();
	}
	public List<POI> buscar(String criterio)
	{
		LocalTime inicio = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		List<POI> resultado = mapa.buscar(criterio);
		Busqueda busqueda = new Busqueda(resultado.size(), criterio, inicio, LocalTime.now(), fecha);
		busquedas.add(busqueda); //TODO poner como un observer mas?
		observersBusqueda.stream().forEach(observer -> observer.seBusco(busqueda));
		return resultado;
	}
	public int busquedasEnFecha(LocalDate fecha)
	{
		return busquedas.stream().filter(Busqueda -> Busqueda.esEnFecha(fecha)).collect(Collectors.toList()).size();
	}
	public int busquedasTotales()
	{
		return busquedas.size();
	}
	public List<Integer> busquedasParciales()
	{
		return busquedas.stream().map(busqueda ->busqueda.getCantidadResultados()).collect(Collectors.toList());
	}
	public List<Busqueda> getBusquedas()
	{
		return busquedas;
	}
	public String getNombre()
	{
		return nombre;
	}
}
