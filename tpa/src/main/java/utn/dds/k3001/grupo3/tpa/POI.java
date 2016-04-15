package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;
import java.util.List;
import java.time.*;
public class POI {
	//TODO agregar constructor
	public String nombre;
	public String calle;
	public String barrio;
	public int  altura;
	public Point posicion;
	protected List<Comuna> listaComunas;

	public boolean estaCerca(Point otraPosicion)
	{
		return (posicion.distance(otraPosicion) <=500);
	}

	public boolean esBuscado(String criterio)
	{
		return nombre.contains(criterio);
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada,String servicio)
	{
		return false;
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada)
	{
		return false;
	}
}
