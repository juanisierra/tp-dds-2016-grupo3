package utn.dds.k3001.grupo3.tpa.tests;
import utn.dds.k3001.grupo3.tpa.*;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class TestsABMC {
	Mapa CABA;
	Comuna comuna1;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias;
	LocalComercial libreriaYenny;
	RepositorioInterno repositorio;
	@Before
	public void init(){
		CABA = new Mapa();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,0.011), new Point(0.011,0.011), new Point (0.011,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(0.01,0.01),libreria,disponibilidadLibrerias);
		repositorio = new RepositorioInterno();
		CABA.agregarOrigenDeDatos(repositorio);
	}
	@Test
	public void testAgregarLibreriaYenny () {
		repositorio.agregarPoi(libreriaYenny);
		Assert.assertEquals(1, CABA.buscar("").size(),0);
	}
	@Test
	public void testEliminarLibreriaYenny() {
		repositorio.agregarPoi(libreriaYenny);
		repositorio.eliminarPoi(libreriaYenny);
		Assert.assertEquals(0, CABA.buscar("").size(),0);
		
	}
	@Test
	public void testListarPOIS(){
		repositorio.agregarPoi(libreriaYenny);
		Assert.assertTrue(CABA.buscar("").contains(libreriaYenny));
	}
}
