package utn.dds.k3001.grupo3.tpa;
import java.time.*;	//los constructores para localTime y localDateTime son metodos of(...)
import java.util.List;
public class Disponibilidad {
	//TODO agregar constructor
	private List<DayOfWeek> diasDisponible;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
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
