package utn.dds.k3001.grupo3.tpa.tests;
import utn.dds.k3001.grupo3.tpa.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class TestsDisponibilidad {

	Rubro libreria;
	Disponibilidad disponibilidadLibrerias,disponibilidadLunes,disponibilidadMediaSemana;
	LocalComercial libreriaYenny;
	CGP cgp1;
	Servicio altaDomicilio, cambioDomicilio;
	Comuna comuna1;
	@Before
	public void init()
	{	comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,11), new Point(11,11), new Point (11,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		cgp1 = new CGP("cgp2","beiro","caballito",100,new Point(10.1,10.1),comuna1);
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
	}
	@Test
	public void testLaLibreriaEstaAbiertaElMiercolesAlMediodia()
	{	
		LocalDateTime miercolesMediodia = LocalDateTime.of(LocalDate.of(2016, 4,13),LocalTime.of(13,0));
		Assert.assertTrue(libreriaYenny.estaDisponible(miercolesMediodia,""));
	}
	@Test
	public void testLibreriaNoEstaDisponibleElSabadoALaNoche()
	{	
		LocalDateTime sabadoNoche = LocalDateTime.of(LocalDate.of(2016, 4,16),LocalTime.of(23,0));
		Assert.assertFalse(libreriaYenny.estaDisponible(sabadoNoche,""));
	}
	@Test
	public void testCgpEstaAbiertoConElServicoAltaDomicilio()
	{	
		LocalDateTime miercolesMediodia = LocalDateTime.of(LocalDate.of(2016, 4,13),LocalTime.of(13,0));
		Assert.assertTrue(cgp1.estaDisponible(miercolesMediodia, "alta domicilio"));
	}
	@Test
	public void testCgpEstaAbiertoConPorLoMenosUnServicio()
	{
		LocalDateTime miercolesMediodia = LocalDateTime.of(LocalDate.of(2016, 4,13),LocalTime.of(13,0));
		Assert.assertTrue(cgp1.estaDisponible(miercolesMediodia,""));
	}
	@Test
	public void testCgpEstaCerradoLosSabados()
	{	
		LocalDateTime sabadoMediodia = LocalDateTime.of(LocalDate.of(2016, 4,16),LocalTime.of(13,0));
		Assert.assertFalse(cgp1.estaDisponible(sabadoMediodia,""));
	}
	@Test
	public void testConVariosHorariosCGP() 
	{
		disponibilidadLunes = new Disponibilidad(LocalTime.of(1, 0), LocalTime.of(3, 0), Arrays.asList(DayOfWeek.MONDAY));
		disponibilidadMediaSemana = new Disponibilidad(LocalTime.of(4, 0), LocalTime.of(5, 0), Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));
		LocalDateTime diaLunes = LocalDateTime.of(LocalDate.of(2016, 4,18),LocalTime.of(4,0));
		LocalDateTime diaViernes = LocalDateTime.of(LocalDate.of(2016, 4, 22),LocalTime.of(4,0));
		LocalDateTime diaViernesLimite = LocalDateTime.of(LocalDate.of(2016, 4, 22),LocalTime.of(5,0));
		cambioDomicilio = new Servicio("cambio domicilio", disponibilidadLunes);
		cambioDomicilio.agregarDisponibilidad(disponibilidadMediaSemana);
		cgp1.agregarServicio(cambioDomicilio);
		Assert.assertFalse(cgp1.estaDisponible(diaLunes,""));
		Assert.assertTrue(cgp1.estaDisponible(diaViernes, ""));
		Assert.assertFalse(cgp1.estaDisponible(diaViernesLimite,""));
	}
}
