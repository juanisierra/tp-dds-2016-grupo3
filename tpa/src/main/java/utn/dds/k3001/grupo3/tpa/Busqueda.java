package utn.dds.k3001.grupo3.tpa;

import java.time.*;

public class Busqueda
{
	private int cantResultados;
	private String criterio;
	private int tiempo;//esta en segundos
	private LocalDate fecha;
	
	public Busqueda(int cantResultados, String criterio, LocalTime hInicio, LocalTime hFin, LocalDate fecha)
	{
		this.cantResultados = cantResultados;
		this.criterio = criterio;
		this.tiempo = distanciaEnSegundos(hInicio, hFin);
		this.fecha = fecha;
	}
	private int distanciaEnSegundos(LocalTime inicio, LocalTime fin)//TODO buscar algun metodo que lo haga de una
	{
		int horas, minutos, segundos, nano;
		horas = fin.getHour() - inicio.getHour();
		minutos = fin.getMinute() - inicio.getMinute();
		segundos = fin.getSecond() - inicio.getSecond();
		nano = fin.getNano() - inicio.getNano();
		return horas*60*60 + minutos*60 + segundos + nano*(10^-9);
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
	public int getTiempo()
	{
		return this.tiempo;
	}
	public LocalDate getfecha()
	{
		return this.fecha;
	}
}
