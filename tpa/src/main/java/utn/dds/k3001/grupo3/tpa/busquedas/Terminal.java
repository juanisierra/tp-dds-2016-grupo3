package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.POI;
@Entity
public class Terminal
{	@Id @GeneratedValue
	private int id;
	private String nombre;
	@Transient //TODO Agregar mapa
	private Mapa mapa;
	@Transient //TODO Agregar Observers
	private List<ObserverBusqueda> observersBusqueda;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	private Comuna comuna;
	public Terminal(String nombre, Mapa mapa){
		this.nombre = nombre;
		this.mapa = mapa;
		this.observersBusqueda = new LinkedList<ObserverBusqueda>();
	}
	public Terminal(){}
	public String getNombre() {
		return nombre;
	}
	public Terminal(String nombre, Mapa mapa,Comuna comuna){
		this.nombre = nombre;
		this.mapa = mapa;
		this.observersBusqueda = new LinkedList<ObserverBusqueda>();
		this.comuna = comuna;
	}
	public void agregarObserverBusqueda(ObserverBusqueda observerBusqueda){
		observersBusqueda.add(observerBusqueda);
	}
	
	public void eliminarObserverBusqueda(ObserverBusqueda observerBusqueda){
		observersBusqueda.remove(observerBusqueda);
	}
	
	public List<POI> buscar(String criterio){
		LocalTime inicio = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		List<POI> resultado = mapa.buscar(criterio);
		Busqueda busqueda = new Busqueda(this,resultado.size(), criterio, inicio, LocalTime.now(), fecha);
		observersBusqueda.stream().forEach(observer -> observer.agregar(busqueda));
		return resultado;
	}
	
	public int cantObserversBusqueda(){
		return observersBusqueda.size();
	}
	public boolean estaEnComuna(String nombre)
	{
		return comuna.getNombre().equals(nombre);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Mapa getMapa() {
		return mapa;
	}
	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}
	public List<ObserverBusqueda> getObserversBusqueda() {
		return observersBusqueda;
	}
	public void setObserversBusqueda(List<ObserverBusqueda> observersBusqueda) {
		this.observersBusqueda = observersBusqueda;
	}
	public Comuna getComuna() {
		return comuna;
	}
	public void setComuna(Comuna comuna) {
		this.comuna = comuna;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
