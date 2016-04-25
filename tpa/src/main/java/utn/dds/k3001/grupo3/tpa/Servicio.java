package utn.dds.k3001.grupo3.tpa;

import java.time.*;
import java.util.LinkedList;
import java.util.List;

public class Servicio 
{
	private String nombre;
	private List<Disponibilidad> listaDisponibilidad;

	public Servicio(String nombre, Disponibilidad disponibilidad){
		this.listaDisponibilidad = new LinkedList<Disponibilidad>();
		this.nombre = nombre;
		this.listaDisponibilidad.add(disponibilidad);
	}
	
	public void agregarDisponibilidad(Disponibilidad disponibilidad){
		this.listaDisponibilidad.add(disponibilidad);
	}
	public void limpiarDisponibilidad(){
		listaDisponibilidad.removeAll(listaDisponibilidad);
	}
	
	String nombre() {
		return nombre;
	}
	
	public boolean estaDisponible(LocalDateTime fecha){
		return listaDisponibilidad.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
}