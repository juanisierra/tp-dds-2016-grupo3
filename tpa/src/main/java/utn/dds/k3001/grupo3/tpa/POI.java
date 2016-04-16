package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.*;

import java.util.LinkedList;
import java.util.List;
import java.time.*;
public class POI 
{
	public String nombre;
	public String calle;
	public String barrio;
	public int  altura;
	public Point posicion;
	protected static List<Comuna> listaComunas = new LinkedList<Comuna>();
	
	public static void agregarComuna(Comuna comuna)
	{
		listaComunas.add(comuna);
	}

	public POI(String nombre, String calle, String barrio, int altura, Point posicion)
	{
		this.nombre = nombre;
		this.calle = calle;
		this.barrio = barrio;
		this.altura = altura;
		this.posicion = posicion;
	}
	public Point getPosicion() //TOOD BORRAR
	{
		return posicion;
	}
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

