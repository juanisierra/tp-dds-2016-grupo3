package utn.dds.k3001.grupo3.tpa;

import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna extends Polygon
{
	private String nombre;
	
	public Comuna(String nombre,List<Point> puntos)
	{
		super(puntos);
		this.nombre = nombre;
	}
	public String nombre()//TODO si a nadie le sirve el nombre, borrar
	{
		return this.nombre;
	}
}
