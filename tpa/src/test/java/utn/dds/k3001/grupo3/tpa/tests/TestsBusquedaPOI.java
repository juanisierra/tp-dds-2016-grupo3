package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.*;
import java.time.LocalTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class TestsBusquedaPOI 
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

	@Before
	public void init()
	{	
		sistema = new Sistema();
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new Point(10,10),114);
		cgp1 = new CGP("cgp2","beiro","caballito",100,new Point(10.1,10.1));
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		sistema.agregarPoi(parada114);
		sistema.agregarPoi(libreriaYenny);
		sistema.agregarPoi(cgp1);
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
