package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.*;
import java.time.temporal.ChronoUnit;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;
	@Entity 
public class Busqueda
{	@Id @GeneratedValue
	private double id;
	private int cantResultados;
	private String criterio;
	private double tiempoDemorado;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fecha;
	@ManyToOne
	private Terminal terminal;
	
	
	public Busqueda(Terminal terminal,int cantResultados, String criterio, LocalTime hInicio, LocalTime hFin, LocalDate fecha){
		this.terminal = terminal;
		this.cantResultados = cantResultados;
		this.criterio = criterio;
		this.tiempoDemorado = ChronoUnit.SECONDS.between(hInicio, hFin);
		this.fecha = fecha;
	}
	
	public Busqueda(){}
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

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public int getCantResultados() {
		return cantResultados;
	}

	public void setCantResultados(int cantResultados) {
		this.cantResultados = cantResultados;
	}

	public double getTiempoDemorado() {
		return tiempoDemorado;
	}

	public void setTiempoDemorado(double tiempoDemorado) {
		this.tiempoDemorado = tiempoDemorado;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
}
