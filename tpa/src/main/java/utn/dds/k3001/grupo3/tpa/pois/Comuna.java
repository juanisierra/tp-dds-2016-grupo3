package utn.dds.k3001.grupo3.tpa.pois;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import utn.dds.k3001.grupo3.tpa.geo.*;

	@Entity
public class Comuna implements java.io.Serializable
{	@Id @GeneratedValue
	private int id;
	private String nombre;
	@OneToOne(cascade=CascadeType.PERSIST)
	private PersistablePolygon limites;
	
	public Comuna(String nombre,List<PersistablePoint> puntos){
		this.limites = new PersistablePolygon(puntos);
		this.nombre = nombre;
	}
	
	public Comuna(String nombreComuna) {
		nombre = nombreComuna;
	}
	
	public boolean estaEnComuna(PersistablePoint punto){
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
	
	public PersistablePolygon getLimites() {
		return limites;
	}
	
	public void setLimites(PersistablePolygon limites) {
		this.limites = limites;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}