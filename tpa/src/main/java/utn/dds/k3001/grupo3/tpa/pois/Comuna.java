package utn.dds.k3001.grupo3.tpa.pois;

import java.util.List;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class Comuna
{
	private String nombre;
	private Polygon limites;
	
	public Comuna(String nombre,List<Point> puntos){
		this.limites = new Polygon(puntos);
		this.nombre = nombre;
	}
	public Comuna(String nombreComuna) {
		nombre = nombreComuna;
	}
	public boolean estaEnComuna(Point punto){
		return limites.isInside(punto);
	}
	public String getNombre() {
		
		return nombre;
	}
}
