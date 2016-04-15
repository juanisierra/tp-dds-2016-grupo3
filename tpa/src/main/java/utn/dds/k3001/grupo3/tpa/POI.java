package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;
import java.util.List;
import java.time.*;
public abstract class POI 
{
	public String nombre;
	public String calle;
	public String barrio;
	public int  altura;
	public Point posicion;
	protected List<Comuna> listaComunas;

	protected void constructorComun(String nombreP, String calleP, String barrioP, int alturaP, Point posicionP)
	{
		this.nombre = nombreP;
		this.calle = calleP;
		this.barrio = barrioP;
		this.altura = alturaP;
		this.posicion = posicionP;
	}
	/*public POI(String nombreP, String calleP, String barrioP, int alturaP, Point posicionP)
	{
		this.constructorComun(nombreP, calleP, barrioP, alturaP, posicionP);
	}*/
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

