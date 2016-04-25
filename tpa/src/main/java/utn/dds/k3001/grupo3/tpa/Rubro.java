package utn.dds.k3001.grupo3.tpa;

public class Rubro 
{
	private String nombre;
	private double distanciaDeCercania;
	
	public Rubro(String nombre, double distanciaDeCercania){
		this.nombre = nombre;
		this.distanciaDeCercania = distanciaDeCercania;
	}
	
	public double distanciaDeCercania() {
		return distanciaDeCercania;
	}
	
	public String nombre() {
		return nombre;
	}
}
