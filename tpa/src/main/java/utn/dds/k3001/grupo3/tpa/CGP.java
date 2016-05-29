package utn.dds.k3001.grupo3.tpa;

import java.util.ArrayList;
import org.uqbar.geodds.*;

public class CGP extends PrestadorDeServicios
{
	private Comuna comunaPropia;
	
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
}
