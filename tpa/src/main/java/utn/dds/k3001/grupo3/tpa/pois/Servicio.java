package utn.dds.k3001.grupo3.tpa.pois;

import java.time.*;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
@Entity
public class Servicio implements java.io.Serializable
{	@Id @GeneratedValue
	private int id;
	private String nombre;
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name ="servicio_id")
	private List<Disponibilidad> listaDisponibilidad;
	
	public Servicio(){}
	
	public Servicio(String nombre, Disponibilidad disponibilidad){
		this.listaDisponibilidad = new LinkedList<Disponibilidad>();
		this.nombre = nombre;
		this.listaDisponibilidad.add(disponibilidad);
	}
	
	public Servicio(String nombre, List<Disponibilidad> disponibilidades){
		this.listaDisponibilidad = new LinkedList<Disponibilidad>();
		this.nombre = nombre;
		this.listaDisponibilidad = disponibilidades;
	}
	
	public void agregarDisponibilidad(Disponibilidad disponibilidad){
		this.listaDisponibilidad.add(disponibilidad);
	}
	
	public void limpiarDisponibilidad(){
		listaDisponibilidad.removeAll(listaDisponibilidad);
	}
	
	public String nombre() {
		return nombre;
	}
	
	public boolean estaDisponible(LocalDateTime fecha){
		return listaDisponibilidad.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	public List<Disponibilidad> getListaDisponibilidad() {
		return listaDisponibilidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setListaDisponibilidad(List<Disponibilidad> listaDisponibilidad) {
		this.listaDisponibilidad = listaDisponibilidad;
	}
}