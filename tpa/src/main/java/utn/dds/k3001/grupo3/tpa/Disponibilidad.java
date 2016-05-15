package utn.dds.k3001.grupo3.tpa;

import java.time.*;	//los constructores para localTime y localDateTime son metodos of(...)
import java.util.Arrays;
import java.util.List;

public class Disponibilidad 
{
	private List<DayOfWeek> diasDisponible;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	
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
}
