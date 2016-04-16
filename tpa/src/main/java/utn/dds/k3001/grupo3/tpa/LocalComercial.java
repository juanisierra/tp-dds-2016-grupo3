package utn.dds.k3001.grupo3.tpa;
import java.time.LocalDateTime;

import org.uqbar.geodds.*;
public class LocalComercial extends POI 
{
	Rubro rubro;
	Disponibilidad disponibilidad;
	
	public LocalComercial(String nombre, String calle, String barrio, int altura, Point posicion,Rubro rubro, Disponibilidad disponibilidad)
	{	super(nombre,calle,barrio,altura,posicion);
		this.rubro = rubro;
		this.disponibilidad = disponibilidad;
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
	@Override
	public boolean estaDisponible(LocalDateTime fechaBuscada)
	{
		return disponibilidad.estaDisponible(fechaBuscada);
	}
}
