package utn.dds.k3001.grupo3.tpa;
import java.time.*;
public class Servicio {
	//TODO agregar constructor
	private String nombre;
	private Disponibilidad disponibilidad;
	public String nombre() 
	{
		return nombre;
	}
	public boolean estaDisponible(LocalDateTime fecha)
	{
		return disponibilidad.estaDisponible(fecha);
	}
}
