package utn.dds.k3001.grupo3.tpa.pois;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

import utn.dds.k3001.grupo3.tpa.geo.*;
@Entity
public class CGP extends PrestadorDeServicios
{	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	private Comuna comunaPropia;
	public CGP(){}
	
	public CGP(String nombre, String calle, String barrio, int altura, Point posicion, Comuna comuna){
		super(nombre,calle,barrio,altura,posicion);
		this.comunaPropia =comuna;
	}
	public CGP(String nombre, String calle, String barrio, int altura, Point posicion, Comuna comuna,ArrayList<Servicio> servicios){
		super(nombre,calle,barrio,altura,posicion);
		this.comunaPropia =comuna;
		this.serviciosOfrecidos = servicios;
	}
	public boolean estaCerca(Point otraPosicion) {
		return comunaPropia.estaEnComuna(otraPosicion);
	}

	public Comuna getComunaPropia() {
		return comunaPropia;
	}

	public void setComunaPropia(Comuna comunaPropia) {
		this.comunaPropia = comunaPropia;
	}
}
