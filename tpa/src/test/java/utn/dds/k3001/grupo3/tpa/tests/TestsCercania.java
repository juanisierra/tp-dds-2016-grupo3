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

public class TestsCercania {
	ParadaColectivo parada114;
	LocalComercial libreriaYenny;
	Comuna comuna1;
	Comuna comuna2;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias;
	Sistema sistema;
	CGP cgp1;
	Servicio altaDomicilio;
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

	}
	@Test
	public void testEstaCercaParadaDeColectivo() 
	{
		Assert.assertTrue(parada114.estaCerca(new Point(10.1,10.1)));
	}
	@Test
	public void testEstaCercaLibreria()
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

}
