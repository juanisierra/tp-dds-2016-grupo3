package utn.dds.k3001.grupo3.tpa;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.uqbar.geodds.*;
public class LocalComercial extends POI 
{
	private Rubro rubro;
	private List<Disponibilidad> listaDisponibilidad = new LinkedList<Disponibilidad>();
	
	public LocalComercial(String nombre, String calle, String barrio, int altura, Point posicion,Rubro rubro, Disponibilidad disponibilidad)
	{	
		super(nombre,calle,barrio,altura,posicion);
		this.rubro = rubro;
		this.listaDisponibilidad.add(disponibilidad);
	}
	public Rubro rubro()//TODO si a nadie le sirve el rubro, borrar
	{
		return this.rubro;
	}
	@Override
	public boolean estaCerca(Point otraPosicion)
	{
		return (posicion.distance(otraPosicion)<= rubro.distancia());		
	}
	public boolean esBuscado(String criterio) 
	{
		return (rubro.nombre().contains(criterio) || nombre.contains(criterio));
	}
	public void agregarDisponibilidad(Disponibilidad disponibilidad)
	{
		this.listaDisponibilidad.add(disponibilidad);
	}
	public void limpiarDisponibilidad()
	{
		listaDisponibilidad.removeAll(listaDisponibilidad);
	}
	@Override
	public boolean estaDisponible(LocalDateTime fecha)
	{
		return listaDisponibilidad.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
}
