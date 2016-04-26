package utn.dds.k3001.grupo3.tpa;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.uqbar.geodds.*;

public class LocalComercial extends POI 
{
	private Rubro rubro;
	private List<Disponibilidad> listaDisponibilidad;
	
	public LocalComercial(String nombre, String calle, String barrio, int altura, Point posicion,Rubro rubro, Disponibilidad disponibilidad){	
		super(nombre,calle,barrio,altura,posicion);
		this.listaDisponibilidad = new LinkedList<Disponibilidad>();
		this.rubro = rubro;
		this.listaDisponibilidad.add(disponibilidad);
	}
	
	@Override
	public boolean estaCerca(Point otraPosicion){
		return (posicion.distance(otraPosicion)<= rubro.distanciaDeCercania());		
	}
	
	public void agregarDisponibilidad(Disponibilidad disponibilidad){
		this.listaDisponibilidad.add(disponibilidad);
	}
	
	public void limpiarDisponibilidad(){
		listaDisponibilidad.removeAll(listaDisponibilidad);
	}
	
	@Override
	public boolean estaDisponible(LocalDateTime fecha,String servicio){
		return listaDisponibilidad.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	public boolean esBuscado(String criterio) {
		return super.esBuscado(criterio) || rubro.nombre().contains(criterio);
	}
}
