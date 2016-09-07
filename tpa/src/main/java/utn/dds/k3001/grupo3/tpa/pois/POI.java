package utn.dds.k3001.grupo3.tpa.pois;


import utn.dds.k3001.grupo3.tpa.geo.*;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

import java.time.*;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class POI 
{	@Id @GeneratedValue
	protected int id;
	protected String nombre;
	@OneToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	protected Point posicion;
	@ElementCollection
	protected List<String> listaEtiquetas;
	@Embedded
	protected Direccion direccion;

	public POI(String nombre, String calle, String barrio, int altura, Point posicion) {
		this.direccion = new Direccion(calle,barrio,altura);
		this.nombre = nombre;
		this.posicion = posicion;
		this.listaEtiquetas = new LinkedList<String>();

	}
	public POI(){
	}
	public long getID(){
	return id;
	}
	public void agregarEtiqueta(String etiqueta){
		this.listaEtiquetas.add(etiqueta);
	}
	
	public boolean estaCerca(Point otraPosicion){
		return (posicion.distance(otraPosicion) <=0.5);
	}
	
	public boolean esBuscado(String criterio){
		return (nombre.contains(criterio) || listaEtiquetas.contains(criterio));
	}
	
	public boolean estaDisponible(LocalDateTime fechaBuscada,String servicio){
		return false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Point getPosicion() {
		return posicion;
	}

	public void setPosicion(Point posicion) {
		this.posicion = posicion;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public void cambiarEtiquetas(List<String> etiquetasNuevas)
	{
		this.listaEtiquetas.clear();
		this.listaEtiquetas.addAll(etiquetasNuevas);
	}
	public List<String> getEtiquetas()
	{
	return listaEtiquetas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getListaEtiquetas() {
		return listaEtiquetas;
	}
	public void setListaEtiquetas(List<String> listaEtiquetas) {
		this.listaEtiquetas = listaEtiquetas;
	}
}
