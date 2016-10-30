package utn.dds.k3001.grupo3.tpa.busquedas;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import utn.dds.k3001.grupo3.tpa.pois.POI;
	@Entity 
public class Busqueda
{	@Id @GeneratedValue
	private int id;
	private int cantResultados;
	private String criterio;
	private double tiempoDemorado;
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fecha;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
	private Terminal terminal;
	private List<POI> resultados;
	
	public Busqueda(Terminal terminal,int cantResultados, String criterio, LocalTime hInicio, LocalTime hFin, LocalDate fecha,List<POI> resultados){
		this.terminal = terminal;
		this.cantResultados = cantResultados;
		this.criterio = criterio;
		this.tiempoDemorado = ChronoUnit.SECONDS.between(hInicio, hFin);
		this.fecha = fecha;
		this.resultados=resultados;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
