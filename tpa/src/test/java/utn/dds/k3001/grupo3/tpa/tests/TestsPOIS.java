package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.*;
import java.awt.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class TestsPOIS 
{	
	ParadaColectivo parada114;
	LocalComercial libreriaYenny;
	Comuna comuna1;
	Comuna comuna2;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias, disp1, disp2;
	Sistema sistema;
	CGP cgp1, cgp2;
	Servicio altaDomicilio, servicio;
	
	//TODO tests
	@Before
	public void init()
	{	
		sistema = new Sistema();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,11), new Point(11,11), new Point (11,0)));
		comuna2 = new Comuna("comuna 2",Arrays.asList(new Point(0,11), new Point(11,11), new Point(11,20), new Point (0,20)));
		POI.agregarComuna(comuna1);
		POI.agregarComuna(comuna2);
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new Point(10,10),114);
		cgp1 = new CGP("cgp1","beiro","caballito",100,new Point(10.2,10.1));
		cgp1 = new CGP("cgp2","beiro","caballito",100,new Point(10.1,10.1));
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		sistema.agregarPoi(parada114);
		sistema.agregarPoi(libreriaYenny);
		sistema.agregarPoi(cgp1);
	}
	@Test
	public void testVariosHorariosCGP() 
	{
		disp1 = new Disponibilidad(LocalTime.of(1, 0), LocalTime.of(3, 0), Arrays.asList(DayOfWeek.MONDAY));
		disp2 = new Disponibilidad(LocalTime.of(4, 0), LocalTime.of(5, 0), Arrays.asList(DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY));
		LocalDateTime diaComun = LocalDateTime.of(LocalDate.of(2016, 4,18),LocalTime.of(4,1));		
		servicio = new Servicio("ola k ase", disp1);
		servicio.agregarDisponibilidad(disp2);
		cgp1.agregarServicio(servicio);
		Assert.assertFalse(cgp1.estaDisponible(diaComun));
	}
	@Test
	public void testCercaniaParadaDeColectivo() 
	{
		Assert.assertTrue(parada114.estaCerca(new Point(10.1,10.1)));
	}
	@Test
	public void testCercaniaLibreria()
	{
		Assert.assertTrue(libreriaYenny.estaCerca(new Point(10.2,10.1)));
	}
	@Test
	public void testEstaCercaCGP()
	{
		Assert.assertTrue(cgp1.estaCerca(new Point(10,10)));
	}
	@Test
	public void testEstaLejosCGP()
	{
		Assert.assertFalse(cgp1.estaCerca(new Point(5,13)));
	}
	@Test
	public void testLibreriaAbierta()
	{	
		LocalDateTime miercolesMediodia = LocalDateTime.of(LocalDate.of(2016, 4,13),LocalTime.of(13,0));
		Assert.assertTrue(libreriaYenny.estaDisponible(miercolesMediodia));
	}
	@Test
	public void testLibreriaNoDisponible()
	{	
		LocalDateTime sabadoMediodia = LocalDateTime.of(LocalDate.of(2016, 4,16),LocalTime.of(13,0));
		Assert.assertFalse(libreriaYenny.estaDisponible(sabadoMediodia));
	}
	@Test
	public void cgpAbiertoAltaDomicilio()
	{	
		LocalDateTime miercolesMediodia = LocalDateTime.of(LocalDate.of(2016, 4,13),LocalTime.of(13,0));
		Assert.assertTrue(cgp1.estaDisponible(miercolesMediodia, "alta domicilio"));
	}
	@Test
	public void cgpAbiertoCualquierCosa()
	{
		LocalDateTime miercolesMediodia = LocalDateTime.of(LocalDate.of(2016, 4,13),LocalTime.of(13,0));
		Assert.assertTrue(cgp1.estaDisponible(miercolesMediodia));
	}
	@Test
	public void cgpCerradoSabados()
	{	
		LocalDateTime sabadoMediodia = LocalDateTime.of(LocalDate.of(2016, 4,16),LocalTime.of(13,0));
		Assert.assertFalse(cgp1.estaDisponible(sabadoMediodia));
	}
	@Test
	public void buscarAltaDomicilio()
	{	
		Assert.assertTrue(sistema.buscar("alta domicilio").contains(cgp1));
	}
	@Test
	public void buscarParada114()
	{
		Assert.assertTrue(sistema.buscar("114").contains(parada114));
	}
	@Test
	public void buscarLibreria()
	{
		Assert.assertTrue(sistema.buscar("libreria").contains(libreriaYenny));
	}
}
