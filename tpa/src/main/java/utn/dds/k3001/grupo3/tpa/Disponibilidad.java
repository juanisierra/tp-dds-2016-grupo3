package utn.dds.k3001.grupo3.tpa;
import java.time.*;	//los constructores para localTime y localDateTime son metodos of(...)
import java.util.Arrays;
import java.util.List;
public class Disponibilidad 
{
	private List<DayOfWeek> diasDisponible;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	
	public Disponibilidad(LocalTime hApertura, LocalTime hCierre,List<DayOfWeek> diasDisponible)
	{
		this.horaApertura = hApertura;
		this.horaCierre  = hCierre;
		this.diasDisponible = diasDisponible;
	}
	public static Disponibilidad horarioBancario() //Builder para Horario Bancario
	{	
		LocalTime horaApertura = LocalTime.of(10,0);
		LocalTime horaCierre = LocalTime.of(15,0);
		List<DayOfWeek> listaDias = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
		return new Disponibilidad(horaApertura, horaCierre,listaDias);
	}
	public static Disponibilidad lunesAViernes(LocalTime hApertura, LocalTime hCierre) //Builder para Lunes a viernes
	{
		List<DayOfWeek> listaDias = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);
		return new Disponibilidad(hApertura, hCierre,listaDias);
	}
	public boolean estaDisponible(LocalDateTime fechaBuscada)
	{
		return this.abreElDia(fechaBuscada.getDayOfWeek()) && this.abreALaHora(fechaBuscada.toLocalTime());
	}
	public boolean abreElDia(DayOfWeek dia)
	{
		return diasDisponible.contains(dia);
	}
	private boolean abreALaHora(LocalTime hora)
	{
		return (hora.compareTo(horaApertura)>0 && hora.compareTo(horaCierre)<0);
	}
}
