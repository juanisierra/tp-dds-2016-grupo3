package utn.dds.k3001.grupo3.tpa;

import java.time.LocalTime;

import org.uqbar.geodds.Point;

public class Banco extends PrestadorDeServicios 
{	
	public Banco(String nombre, String calle, String barrio, int altura, Point posicion){
		super(nombre,calle,barrio,altura,posicion);
	}
	
	public void agregarServicio(Servicio servicio){	
		servicio.limpiarDisponibilidad();
		servicio.agregarDisponibilidad(Disponibilidad.lunesAViernes(LocalTime.of(10,0),LocalTime.of(15,0)));
		serviciosOfrecidos.add(servicio);
	}
}