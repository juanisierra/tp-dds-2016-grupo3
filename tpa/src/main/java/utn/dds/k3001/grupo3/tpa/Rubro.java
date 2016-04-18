package utn.dds.k3001.grupo3.tpa;

public class Rubro 
{
	private String nombre;
	private double distanciaDeCercania;
	
	public Rubro(String nombreP, double distanciaDeCercaniaP)
	{
		this.nombre = nombreP;
		this.distanciaDeCercania = distanciaDeCercaniaP;
	}
	public double distancia() 
	{
		return distanciaDeCercania;
	}
	public String nombre() 
	{
		return nombre;
	}
}
