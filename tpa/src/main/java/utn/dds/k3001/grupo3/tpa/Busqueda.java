package utn.dds.k3001.grupo3.tpa;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Busqueda
{
	private int cantResultados;
	private String criterio;
	private double tiempo;//esta en segundos
	private LocalDate fecha;
	
	public Busqueda(int cantResultados, String criterio, LocalTime hInicio, LocalTime hFin, LocalDate fecha)
	{
		this.cantResultados = cantResultados;
		this.criterio = criterio;
		this.tiempo = ChronoUnit.SECONDS.between(hInicio, hFin);
		this.fecha = fecha;
	}
	public boolean esEnFecha(LocalDate fecha)
	{
		return this.fecha.equals(fecha);
	}
	public int getCantidadResultados()
	{
		return this.cantResultados;
	}
	public String getcriterio()
	{
		return this.criterio;
	}
	public double getTiempo()
	{
		return this.tiempo;
	}
	public LocalDate getfecha()
	{
		return this.fecha;
	}
}
