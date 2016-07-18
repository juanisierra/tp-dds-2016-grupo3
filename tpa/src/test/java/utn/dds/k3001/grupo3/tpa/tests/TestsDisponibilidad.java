package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

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
	Comuna comuna1;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias,disponibilidadLunes,disponibilidadMediaSemana;
	LocalComercial libreriaYenny;
	CGP cgp1, cgp2;
	Servicio altaDomicilio, cambioDomicilio;
	
	@Before
	public void init()
	{	comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,11), new Point(11,11), new Point (11,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		cgp1 = new CGP("cgp1","beiro","caballito",100,new Point(10.1,10.1),comuna1);
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		
		cgp2= new CGP("cgp2","beiro","rivadavia",4000,new Point(10.1,10.1),comuna1);
		disponibilidadLunes = new Disponibilidad(LocalTime.of(1, 0), LocalTime.of(3, 0), Arrays.asList(DayOfWeek.MONDAY));
		disponibilidadMediaSemana = new Disponibilidad(LocalTime.of(4, 0), LocalTime.of(5, 0), Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));
		cambioDomicilio = new Servicio("cambio domicilio", disponibilidadLunes);
		cambioDomicilio.agregarDisponibilidad(disponibilidadMediaSemana);
		cgp2.agregarServicio(cambioDomicilio);
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
	public void testDisponibleAPrimeraHora() 
	{
		LocalDateTime diaLunes = LocalDateTime.of(LocalDate.of(2016, 4,18),LocalTime.of(1,0));
		Assert.assertTrue(cgp2.estaDisponible(diaLunes,""));
	}
	@Test
	public void testNoDisponibleAUltimaHora()
	{
		LocalDateTime diaLunes = LocalDateTime.of(LocalDate.of(2016, 4,18),LocalTime.of(3,0));
		Assert.assertFalse(cgp2.estaDisponible(diaLunes,""));
	}
	@Test
	public void testCGPDisponibleLunes()
	{
		LocalDateTime diaLunes = LocalDateTime.of(LocalDate.of(2016, 4,18),LocalTime.of(2,0));
		Assert.assertTrue(cgp2.estaDisponible(diaLunes,""));
	}
	@Test
	public void testCGPDisponibleMartes()
	{
		LocalDateTime diaViernes = LocalDateTime.of(LocalDate.of(2016, 4, 22),LocalTime.of(4,6));
		Assert.assertTrue(cgp2.estaDisponible(diaViernes, ""));
	}
	@Test
	public void testNoEncuentraServicio()
	{
		LocalDateTime diaViernes = LocalDateTime.of(LocalDate.of(2016, 4, 22),LocalTime.of(4,6));
		Assert.assertFalse(cgp1.estaDisponible(diaViernes, "esteServicioNoExiste"));
	}
}
