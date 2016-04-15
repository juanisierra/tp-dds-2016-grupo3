package utn.dds.k3001.grupo3;
import org.uqbar.geodds.*;
public class LocalComercial extends POI {
	Rubro rubro;
	@Override
	public boolean estaCerca(Point otraPosicion)
	{
		return (posicion.distance(otraPosicion)<= rubro.distancia());
		
	}
	public boolean esBuscado(String criterio) 
	{
		return (rubro.nombre().contains(criterio) || nombre.contains(criterio));
	}
	
}
