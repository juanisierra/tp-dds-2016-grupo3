package utn.dds.k3001.grupo3;

import org.uqbar.geodds.*;
import java.util.List;

public class POI {
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
}
