package utn.dds.k3001.grupo3.tpa.pois;

import java.time.*;
import java.util.LinkedList;
import java.util.List;
import utn.dds.k3001.grupo3.tpa.geo.*;
public abstract class PrestadorDeServicios extends POI 
{
	protected List<Servicio> serviciosOfrecidos;
	
	public PrestadorDeServicios(String nombre, String calle, String barrio, int altura, Point posicion){
		super(nombre,calle,barrio,altura,posicion);
		this.serviciosOfrecidos = new LinkedList<Servicio>();
	}
	public void agregarServicio(Servicio servicio){
		this.serviciosOfrecidos.add(servicio);
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada, String servicio) {	
		return serviciosOfrecidos.stream().anyMatch(servicioBuscado -> (servicioBuscado.nombre().contains(servicio) && servicioBuscado.estaDisponible(fechaBuscada)));
	}
	public boolean esBuscado(String criterio){
		return super.esBuscado(criterio) || this.tieneServicio(criterio);
	}
	private boolean tieneServicio(String servicio) {
		return serviciosOfrecidos.stream().anyMatch(unServicio -> unServicio.nombre().contains(servicio));
	}
	public List<Servicio> getServiciosOfrecidos() {
		return serviciosOfrecidos;
	}
}
