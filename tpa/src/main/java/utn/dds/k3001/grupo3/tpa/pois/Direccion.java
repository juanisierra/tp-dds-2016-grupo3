package utn.dds.k3001.grupo3.tpa.pois;

public class Direccion {
	
	private String calle;
	private String barrio;
	private int altura;
	
	public Direccion(String calle, String barrio, int altura) {
		this.calle = calle;
		this.barrio = barrio;
		this.altura = altura;
	}
	public String getCalle() {
		return calle;
	}
	public String getBarrio() {
		return barrio;
	}
	public int getAltura() {
		return altura;
	}
}
