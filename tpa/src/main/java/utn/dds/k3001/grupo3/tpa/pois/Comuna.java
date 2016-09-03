package utn.dds.k3001.grupo3.tpa.pois;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import utn.dds.k3001.grupo3.tpa.geo.*;

	@Entity
public class Comuna
{	@Id @GeneratedValue
	private int id;
	private String nombre;
	@OneToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	private Polygon limites;
	
	public Comuna(String nombre,List<Point> puntos){
		this.limites = new Polygon(puntos);
		this.nombre = nombre;
	}
	public Comuna(String nombreComuna) {
		nombre = nombreComuna;
	}
	public boolean estaEnComuna(Point punto){
		return limites.isInside(punto);
	}
	public Comuna(){}
	public String getNombre() {
		
		return nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Polygon getLimites() {
		return limites;
	}
	public void setLimites(Polygon limites) {
		this.limites = limites;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
