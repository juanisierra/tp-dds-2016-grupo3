package utn.dds.k3001.grupo3;

public class ParadaColectivo extends POI {
	Integer linea;
	@Override
	public boolean estaCerca(double latitud2,double longitud2)
	{
		return (this.distancia(latitud2, longitud2) <=100);
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
