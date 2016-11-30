package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.POI;

@Entity
public class Terminal
{	
	@Id @GeneratedValue
	private int id;
	
	private String nombre;
	
	@ElementCollection
	private List<AccionesBusqueda> observersBusqueda;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Comuna comuna;
	
	public Terminal(String nombre){
		this.nombre = nombre;
		this.observersBusqueda = new LinkedList<AccionesBusqueda>();
	}
	
	public Terminal(){	//Builder para hibernate
		this.observersBusqueda = new LinkedList<AccionesBusqueda>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Terminal(String nombre, Comuna comuna){
		this.nombre = nombre;
		this.observersBusqueda = new LinkedList<AccionesBusqueda>();
		this.comuna = comuna;
	}
	
	public void agregarObserverBusqueda(AccionesBusqueda observerBusqueda){
		if(!observersBusqueda.contains(observerBusqueda)){
			observersBusqueda.add(observerBusqueda);
		}
	}
	
	public boolean tengoObserver(AccionesBusqueda observerBusqueda){
		return observersBusqueda.contains(observerBusqueda);
	}
	
	public void eliminarObserverBusqueda(AccionesBusqueda observerBusqueda){
		if(observersBusqueda.contains(observerBusqueda)){
			observersBusqueda.remove(observerBusqueda);
		}
	}
	
	public List<POI> buscar(String criterio){
		LocalTime inicio = LocalTime.now();
		LocalDate fecha = LocalDate.now();
		List<POI> resultado = Mapa.getInstance().buscar(criterio);
		Busqueda busqueda = new Busqueda(this,resultado.size(), criterio, inicio, LocalTime.now(), fecha,resultado);
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
	
	public List<AccionesBusqueda> getObserversBusqueda() {
		return observersBusqueda;
	}
	
	public void setObserversBusqueda(List<AccionesBusqueda> observersBusqueda) {
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
