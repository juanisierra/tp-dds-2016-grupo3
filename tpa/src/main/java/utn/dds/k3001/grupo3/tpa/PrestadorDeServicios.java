package utn.dds.k3001.grupo3.tpa;

import java.time.*;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.geodds.Point;

public abstract class PrestadorDeServicios extends POI 
{
	protected List<Servicio> serviciosOfrecidos;
	
	public PrestadorDeServicios(String nombre, String calle, String barrio, int altura, Point posicion)
	{
		super(nombre,calle,barrio,altura,posicion);
		this.serviciosOfrecidos = new LinkedList<Servicio>();
	}
	public void agregarServicio(Servicio servicio)
	{
		serviciosOfrecidos.add(servicio);
	}	
	private boolean hayServicio(String criterio) 
	{
		return serviciosOfrecidos.stream().anyMatch(servicio -> servicio.nombre().contains(criterio));
	}
	public boolean esBuscado(String criterio) 
	{
		return (this.hayServicio(criterio) || nombre.contains(criterio)); 
		//Ademas
		// de
		// buscar
		// los
		// servicios
		// buscamos
		// su
		// nombre,
		// tambien en el banco buscamos que tenga el servicio, para complementar
		// la busqueda comun por nombre
	}
	private Servicio buscarServicio(String nombre) 
	{
		return serviciosOfrecidos.stream().filter(servicio -> servicio.nombre().contains(nombre)).findFirst().get();
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada, String servicio) 
	{
		return this.buscarServicio(servicio).estaDisponible(fechaBuscada);
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada) 
	{
		return serviciosOfrecidos.stream().anyMatch(servicio -> servicio.estaDisponible(fechaBuscada));
	}
}
