package utn.dds.k3001.grupo3.tpa.pois;

import java.time.*;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import utn.dds.k3001.grupo3.tpa.geo.*;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //TODO Agregar single-table o ver como
public abstract class PrestadorDeServicios extends POI 
{	@ManyToMany //TODO Revisar relacion
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	protected List<Servicio> serviciosOfrecidos;
	public PrestadorDeServicios(){}
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
	public void setServiciosOfrecidos(List<Servicio> serviciosOfrecidos) {
		this.serviciosOfrecidos = serviciosOfrecidos;
	}
}
