package utn.dds.k3001.grupo3.tpa.pois;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rubro implements java.io.Serializable
{	@Id @GeneratedValue
	private int id;
	private String nombre;
	private double distanciaDeCercania;
	public Rubro(){}
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDistanciaDeCercania() {
		return distanciaDeCercania;
	}

	public void setDistanciaDeCercania(double distanciaDeCercania) {
		this.distanciaDeCercania = distanciaDeCercania;
	}
}
