package utn.dds.k3001.grupo3.tpa;
import java.time.LocalDateTime;

import org.uqbar.geodds.*;
public class LocalComercial extends POI 
{
	Rubro rubro;
	Disponibilidad disponibilidad;
	
	public LocalComercial(Rubro rubroP, Disponibilidad disponibilidadP, String nombreP, String calleP, String barrioP, int alturaP, Point posicionP)
	{
		this.rubro = rubroP;
		this.disponibilidad = disponibilidadP;
		this.constructorComun(nombreP, calleP, barrioP, alturaP, posicionP);
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
