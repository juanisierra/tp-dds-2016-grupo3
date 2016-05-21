package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BusquedasManager
{
	private List<Busqueda> busquedas;
	
	public BusquedasManager()
	{
		busquedas = new LinkedList<Busqueda>(); 
	}
	public void agregar(Busqueda busqueda)
	{
		busquedas.add(busqueda);
	}
	public List<Busqueda> busquedasEnFecha(LocalDate fecha)
	{
		return busquedas.stream().filter(Busqueda -> Busqueda.esEnFecha(fecha)).collect(Collectors.toList());
	}
}
