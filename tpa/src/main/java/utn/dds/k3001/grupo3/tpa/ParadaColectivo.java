package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;
import java.time.*;
public class ParadaColectivo extends POI 
{
	Integer linea;
	
	public ParadaColectivo(Integer lineaP, String nombreP, String calleP, String barrioP, int alturaP, Point posicionP)
	{
		this.linea = lineaP;
		this.constructorComun(nombreP, calleP, barrioP, alturaP, posicionP);
	}
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
