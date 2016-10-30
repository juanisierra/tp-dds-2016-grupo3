package utn.dds.k3001.grupo3.tpa.pois;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import utn.dds.k3001.grupo3.tpa.geo.*;
@Entity
public class LocalComercial extends POI implements java.io.Serializable
{	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	private Rubro rubro;
	@OneToMany
	@JoinColumn(name = "local_id")
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	private List<Disponibilidad> listaDisponibilidad;
	public LocalComercial(){}
	public LocalComercial(String nombre, String calle, String barrio, int altura, PersistablePoint posicion,Rubro rubro, Disponibilidad disponibilidad){	
		super(nombre,calle,barrio,altura,posicion);
		this.listaDisponibilidad = new LinkedList<Disponibilidad>();
		this.rubro = rubro;
		this.listaDisponibilidad.add(disponibilidad);
	}
	
	@Override
	public boolean estaCerca(PersistablePoint otraPosicion){
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

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public List<Disponibilidad> getListaDisponibilidad() {
		return listaDisponibilidad;
	}

	public void setListaDisponibilidad(List<Disponibilidad> listaDisponibilidad) {
		this.listaDisponibilidad = listaDisponibilidad;
	}

}
