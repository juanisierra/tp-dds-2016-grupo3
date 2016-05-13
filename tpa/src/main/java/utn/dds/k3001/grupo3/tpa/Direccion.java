package utn.dds.k3001.grupo3.tpa;

public class Direccion {
	
	private String calle;
	private String barrio;
	private int altura;
	
	public Direccion(String calle, String barrio, int altura) {
		this.calle = calle;
		this.barrio = barrio;
		this.altura = altura;
	}
	public boolean equals(Direccion otra){
		return this.calle.equals(otra.calle)&&this.barrio.equals(otra.barrio); //TODO Agregar altura
	}
}
