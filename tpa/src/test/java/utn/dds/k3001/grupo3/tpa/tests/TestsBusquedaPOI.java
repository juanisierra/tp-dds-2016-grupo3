package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.ParadaColectivo;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

import java.time.LocalTime;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utn.dds.k3001.grupo3.tpa.geo.*;

public class TestsBusquedaPOI 
{	
	Mapa CABA;
	Comuna comuna1;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias;
	LocalComercial libreriaYenny;
	ParadaColectivo parada114;
	CGP cgp1, cgp2;
	Servicio altaDomicilio;
	
	@Before
	public void init(){	
		CABA = Mapa.getInstance();
		CABA.resetMapa();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,11), new Point(11,11), new Point (11,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new Point(10,10),114);
		cgp1 = new CGP("cgp2","beiro","caballito",100,new Point(10.1,10.1),comuna1);
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		CABA.agregarPoi(parada114);
		CABA.agregarPoi(libreriaYenny);
		CABA.agregarPoi(cgp1);
	}
	
	@Test
	public void testBuscarAltaDomicilio(){	
		Assert.assertTrue(CABA.buscar("alta domicilio").contains(cgp1));
	}
	
	@Test
	public void testBuscarParada114(){
		Assert.assertTrue(CABA.buscar("114").contains(parada114));
	}
	
	@Test
	public void testBuscarLibreria(){
		Assert.assertTrue(CABA.buscar("libreria").contains(libreriaYenny));
	}
}
