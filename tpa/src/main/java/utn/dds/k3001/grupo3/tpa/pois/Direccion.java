package utn.dds.k3001.grupo3.tpa.pois;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Direccion {
	
	private String calle;
	private String barrio;
	private int altura;
	public Direccion(){}
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
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
}
