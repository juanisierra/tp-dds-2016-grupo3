package utn.dds.k3001.grupo3;

import org.uqbar.geodds.*;

public class ParadaColectivo extends POI {
	Integer linea;
	@Override
	public boolean estaCerca(Point otraPosicion)
	{
		return (posicion.distance(otraPosicion) <=100);
	}
	public boolean estaDisponible()
	{
		return true;
	}
	public boolean esBuscado(String criterio)
	{	
		return(linea.toString().equals(criterio) || nombre.contains(criterio));
	}
}
