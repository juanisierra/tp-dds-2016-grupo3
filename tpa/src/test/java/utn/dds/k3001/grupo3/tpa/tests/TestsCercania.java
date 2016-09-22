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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.dds.k3001.grupo3.tpa.geo.*;
public class TestsCercania implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{
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
	{	beginTransaction();
		CABA = Mapa.getInstance();
		CABA.resetMapa();
		comuna1 = new Comuna("comuna 1",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,0.011), new PersistablePoint(0.011,0.011), new PersistablePoint (0.011,0)));
		libreria = new Rubro("libreria",50);
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new PersistablePoint(0.01,0.01),libreria,disponibilidadLibrerias);
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new PersistablePoint(0.01,0.01),114);
		cgp1 = new CGP("cgp1","beiro","caballito",100,new PersistablePoint(0.0102,0.0101),comuna1);

	}
	@After
	public void end(){
		rollbackTransaction();
	}
	@Test
	public void testEstaCercaParadaDeColectivo() 
	{
		Assert.assertTrue(parada114.estaCerca(new PersistablePoint(0.0101,0.0101)));
	}
	
	@Test
	public void testEstaCercaLibreria()
	{
		Assert.assertTrue(libreriaYenny.estaCerca(new PersistablePoint(0.0102,0.0101)));
	}
	@Test
	public void testEstaCercaCGP()
	{
		Assert.assertTrue(cgp1.estaCerca(new PersistablePoint(0.01,0.01)));
	}
	@Test
	public void testEstaLejosCGP()
	{
		Assert.assertFalse(cgp1.estaCerca(new PersistablePoint(0.005,0.013)));
	}

}
