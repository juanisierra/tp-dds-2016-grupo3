package utn.dds.k3001.grupo3.tpa.pois;


import utn.dds.k3001.grupo3.tpa.geo.*;
import java.util.LinkedList;
import java.util.List;
import java.time.*;

public class POI 
{
	protected String nombre;
	protected Point posicion;
	protected List<String> listaEtiquetas;
	protected Direccion direccion;
	protected long id;
	public static long maxID = 0;
	public POI(String nombre, String calle, String barrio, int altura, Point posicion) {
		this.direccion = new Direccion(calle,barrio,altura);
		this.nombre = nombre;
		this.posicion = posicion;
		this.listaEtiquetas = new LinkedList<String>();
		this.id = ++maxID;
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
}
