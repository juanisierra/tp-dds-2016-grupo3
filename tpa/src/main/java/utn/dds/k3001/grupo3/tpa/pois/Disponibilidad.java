package utn.dds.k3001.grupo3.tpa.pois;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import utn.dds.k3001.grupo3.tpa.converters.LocalTimeConverter;
@Entity
public class Disponibilidad 
{	@Id @GeneratedValue
	private int id;
	@ElementCollection
	private List<DayOfWeek> diasDisponible;
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime horaApertura;
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime horaCierre;
	public Disponibilidad(){}
	public Disponibilidad(LocalTime horaApertura, LocalTime horaCierre,List<DayOfWeek> diasDisponible){
		this.horaApertura = horaApertura;
		this.horaCierre  = horaCierre;
		this.diasDisponible = diasDisponible;
	}
	
	public static Disponibilidad lunesAViernes(LocalTime horaApertura, LocalTime horaCierre){ //Builder para Lunes a viernes
		List<DayOfWeek> listaDias = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
		return new Disponibilidad(horaApertura, horaCierre,listaDias);
	}
	
	public boolean estaDisponible(LocalDateTime fechaBuscada){
		return this.abreElDia(fechaBuscada.getDayOfWeek()) && this.abreALaHora(fechaBuscada.toLocalTime());
	}
	
	public boolean abreElDia(DayOfWeek dia){
		return diasDisponible.contains(dia);
	}

	private boolean abreALaHora(LocalTime hora){
		return (hora.compareTo(horaApertura)>=0 && hora.compareTo(horaCierre)<0);
	}
	
	public List<DayOfWeek> getDiasDisponible() {
		return diasDisponible;
	}

	public LocalTime getHoraApertura() {
		return horaApertura;
	}

	public LocalTime getHoraCierre() {
		return horaCierre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDiasDisponible(List<DayOfWeek> diasDisponible) {
		this.diasDisponible = diasDisponible;
	}

	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}

	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
}
