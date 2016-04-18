package utn.dds.k3001.grupo3.tpa;

import org.uqbar.geodds.Point;
import java.time.*;

public class Banco extends PrestadorDeServicios 
{	
	public Banco(String nombre, String calle, String barrio, int altura, Point posicion)
	{
		super(nombre,calle,barrio,altura,posicion);
	}
	@Override
	public void agregarServicio(Servicio servicio)
	{	
		Servicio servicioBancario = servicio;
		servicioBancario.agregarDisponibilidad(Disponibilidad.horarioBancario());
		serviciosOfrecidos.add(servicioBancario);
	}
}
