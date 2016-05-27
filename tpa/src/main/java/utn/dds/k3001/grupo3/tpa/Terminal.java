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
	
	public Terminal(String nombre, Mapa mapa)
	{
		this.nombre = nombre;
		this.mapa = mapa;
		this.busquedas = new LinkedList<Busqueda>();
	}
	public List<POI> buscar(String criterio)
	{
		LocalTime inicio = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		List<POI> resultado = mapa.buscar(criterio);
		busquedas.add(new Busqueda(resultado.size(), criterio, inicio, LocalTime.now(), fecha));
		return resultado;
	}
	public List<Busqueda> busquedasEnFecha(LocalDate fecha)
	{
		return busquedas.stream().filter(Busqueda -> Busqueda.esEnFecha(fecha)).collect(Collectors.toList());
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
