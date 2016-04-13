package utn.dds.k3001.grupo3;
public class POI {
	public String nombre;
	public String calle;
	public String barrio;
	public int  altura;
	public double latitud;
	public double longitud;
	
	protected double distancia(double latitud2, double longitud2)
	{	
		return Math.sqrt(Math.pow((latitud-latitud2),2)+ Math.pow(longitud-longitud2, 2));
	}
	public boolean estaCerca(double latitud2,double longitud2)
	{
		return (this.distancia(latitud2, longitud2) <=500);
	}
	
	public boolean esBuscado(String criterio)
	{
		return nombre.contains(criterio);
	}
}
