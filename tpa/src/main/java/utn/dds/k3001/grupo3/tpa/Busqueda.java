package utn.dds.k3001.grupo3.tpa;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Busqueda
{
	private int cantResultados;
	private String criterio;
	private double tiempoDemorado;
	private LocalDate fecha;
	private Terminal terminal;
	public Busqueda(Terminal terminal,int cantResultados, String criterio, LocalTime hInicio, LocalTime hFin, LocalDate fecha){
		this.terminal = terminal;
		this.cantResultados = cantResultados;
		this.criterio = criterio;
		this.tiempoDemorado = ChronoUnit.SECONDS.between(hInicio, hFin);
		this.fecha = fecha;
	}
	public Terminal getTerminal(){
	return terminal;
	}
	public boolean esEnFecha(LocalDate fecha){
		return this.fecha.equals(fecha);
	}
	
	public int getCantidadResultados(){
		return this.cantResultados;
	}
	
	public double getTiempo(){
		return this.tiempoDemorado;
	}
	
	public LocalDate getFecha(){
		return this.fecha;
	}
	public String getCriterio() {
		return criterio;
	}
}
