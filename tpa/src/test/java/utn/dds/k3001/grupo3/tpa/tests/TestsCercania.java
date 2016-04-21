package utn.dds.k3001.grupo3.tpa.tests;
import utn.dds.k3001.grupo3.tpa.*;
import java.time.LocalTime;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class TestsCercania {
	Mapa CABA;
	Comuna comuna1;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias;
	LocalComercial libreriaYenny;
	ParadaColectivo parada114;
	CGP cgp1;
	Servicio altaDomicilio;
	
	@Before
	public void init()
	{	
		CABA = new Mapa();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,11), new Point(11,11), new Point (11,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new Point(10,10),114);
		cgp1 = new CGP("cgp1","beiro","caballito",100,new Point(10.2,10.1),comuna1);

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
