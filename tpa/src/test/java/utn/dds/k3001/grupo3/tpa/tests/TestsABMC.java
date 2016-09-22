package utn.dds.k3001.grupo3.tpa.tests;
import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;

import java.time.LocalTime;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.dds.k3001.grupo3.tpa.geo.*;
public class TestsABMC extends AbstractPersistenceTest implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{
	
	Mapa CABA;
	Comuna comuna1;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias;
	LocalComercial libreriaYenny;

	@Before
	public void init(){
		beginTransaction();
		CABA = Mapa.getInstance();
		CABA.resetMapa();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,0.011), new PersistablePoint(0.011,0.011), new PersistablePoint (0.011,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new PersistablePoint(0.01,0.01),libreria,disponibilidadLibrerias);
		
		
	}
	@Test
	public void testAgregarLibreriaYenny () {
		CABA.agregarPoi(libreriaYenny);
		Assert.assertEquals(1, CABA.buscar("").size(),0);
		
	}
	@Test
	public void testEliminarLibreriaYenny() {
		CABA.agregarPoi(libreriaYenny);
		CABA.eliminarPoi(libreriaYenny);
		Assert.assertEquals(0, CABA.buscar("").size(),0);
		
	}
	@Test
	public void testListarPOIS(){
		CABA.agregarPoi(libreriaYenny);
		Assert.assertTrue(CABA.buscar("").contains(libreriaYenny));
	}
}
