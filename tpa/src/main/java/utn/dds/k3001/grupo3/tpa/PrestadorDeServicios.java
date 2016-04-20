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
		this.serviciosOfrecidos.add(servicio);
		this.listaEtiquetas.add(servicio.nombre());
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
