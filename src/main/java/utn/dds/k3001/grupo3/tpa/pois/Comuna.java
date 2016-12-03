package utn.dds.k3001.grupo3.tpa.pois;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import utn.dds.k3001.grupo3.tpa.geo.*;

@Entity
public class Comuna implements java.io.Serializable{
	@Id @GeneratedValue
	private int id;
	private String nombre;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<PersistablePoint> limites;
	
	public Comuna(String nombre, List<PersistablePoint> puntos){
		this.limites = puntos;
		this.nombre = nombre;
	}
	
	public Comuna(String nombreComuna) {
		nombre = nombreComuna;
	}
	
	public boolean estaEnComuna(PersistablePoint punto){
		return polygon().isInside(punto.toPoint());
	}
	
	private Polygon polygon(){
		List<Point> puntosPoint = limites.stream().map(p -> p.toPoint()).collect(Collectors.toList());
		return new Polygon(puntosPoint);
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
	
	public void setLimites( List<PersistablePoint>  limites) {
		this.limites = limites;
	}
	
	public List<PersistablePoint> getLimites(){
		return limites;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}