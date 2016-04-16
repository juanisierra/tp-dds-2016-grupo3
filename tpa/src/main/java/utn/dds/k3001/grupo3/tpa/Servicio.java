package utn.dds.k3001.grupo3.tpa;
import java.time.*;
public class Servicio 
{
	private String nombre;
	private Disponibilidad disponibilidad;

	public Servicio(String nombreP, Disponibilidad disponibilidadP)
	{
		this.nombre = nombreP;
		this.disponibilidad = disponibilidadP;
	}
	public void setDisponibilidad(Disponibilidad disponibilidad)
	{
		this.disponibilidad=disponibilidad;
	}
	String nombre() 
	{
		return nombre;
	}
	public boolean estaDisponible(LocalDateTime fecha)
	{
		return disponibilidad.estaDisponible(fecha);
	}
}
