package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;
import java.time.*;
public class ParadaColectivo extends POI {
	//TODO agregar constructor
	Integer linea;
	@Override
	public boolean estaCerca(Point otraPosicion)
	{
		return (posicion.distance(otraPosicion) <=100);
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada)
	{
		return true;
	}

	public boolean esBuscado(String criterio)
	{	
		return(linea.toString().equals(criterio) || nombre.contains(criterio));
	}
}
