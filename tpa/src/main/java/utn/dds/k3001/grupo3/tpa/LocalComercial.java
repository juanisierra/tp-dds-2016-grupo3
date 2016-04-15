package utn.dds.k3001.grupo3.tpa;
import java.time.LocalDateTime;

import org.uqbar.geodds.*;
public class LocalComercial extends POI {
	//TODO agregar constructor
	Rubro rubro;
	Disponibilidad disponibilidad;
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
