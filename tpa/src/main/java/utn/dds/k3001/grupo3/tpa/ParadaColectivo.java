package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;
import java.time.*;

public class ParadaColectivo extends POI 
{
	private Integer linea;
	
	public ParadaColectivo(String nombre, String calle, String barrio, int altura, Point posicion, Integer linea)
	{	
		super(nombre,calle,barrio,altura,posicion);
		this.linea = linea;
	}

	@Override
	public boolean estaCerca(Point otraPosicion)
	{
		return (posicion.distance(otraPosicion) <=100);
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada,String servicio)
	{
		return true;
	}
	public boolean esBuscado(String criterio) {
		return super.esBuscado(criterio) || linea.toString().contains(criterio);
	}
}
