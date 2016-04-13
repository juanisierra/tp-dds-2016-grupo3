package utn.dds.k3001.grupo3;

public class LocalComercial extends POI {
	Rubro rubro;
	@Override
	public boolean estaCerca(double latitud2,double longitud2)
	{
		return (this.distancia(latitud2,longitud2)<= rubro.distancia());
		
	}
	public boolean esBuscado(String criterio) 
	{
		return (rubro.nombre().contains(criterio) || nombre.contains(criterio));
	}
	
}
