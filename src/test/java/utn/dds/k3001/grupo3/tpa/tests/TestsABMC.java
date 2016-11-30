package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import java.time.LocalTime;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.dds.k3001.grupo3.tpa.geo.*;
public class TestsABMC extends AbstractPersistenceTest implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{
	
	Comuna comuna1;
	Rubro libreria;
	Disponibilidad disponibilidadLibrerias;
	LocalComercial libreriaYenny;

	@Before
	public void init(){
		beginTransaction();
		Mapa.getInstance().resetMapa();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,0.011), new PersistablePoint(0.011,0.011), new PersistablePoint (0.011,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new PersistablePoint(0.01,0.01),libreria,disponibilidadLibrerias);
	}
	@Test
	public void testAgregarLibreriaYenny () {
		Mapa.getInstance().agregarPoi(libreriaYenny);
		Assert.assertEquals(1, Mapa.getInstance().buscar("").size(),0);
		
	}
	@Test
	public void testEliminarLibreriaYenny() {
		Mapa.getInstance().agregarPoi(libreriaYenny);
		Mapa.getInstance().eliminarPoi(libreriaYenny);
		Assert.assertEquals(0, Mapa.getInstance().buscar("").size(),0);
		
	}
	@Test
	public void testListarPOIS(){
		Mapa.getInstance().agregarPoi(libreriaYenny);
		Assert.assertTrue(Mapa.getInstance().buscar("").contains(libreriaYenny));
	}
}
