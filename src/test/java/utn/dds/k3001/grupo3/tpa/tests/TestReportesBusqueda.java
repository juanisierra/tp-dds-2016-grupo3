package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.ParadaColectivo;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.dds.k3001.grupo3.tpa.geo.*;

public class TestReportesBusqueda extends AbstractPersistenceTest implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps
{
	Terminal terminal1, terminalMedrano;
	RepositorioBusquedas repositorioBusquedas;
	Rubro libreria;
	Comuna comuna1;
	Disponibilidad disponibilidadLibrerias;
	ParadaColectivo parada114;
	CGP cgp2;
	Servicio altaDomicilio;
	@Before
	public void init(){	
		beginTransaction();
		Mapa.getInstance().resetMapa();
		RepositorioBusquedas.getInstance().reset();
		terminal1 = new Terminal("teminal1");
		terminalMedrano = new Terminal("TerminalMedrano");
		repositorioBusquedas = RepositorioBusquedas.getInstance();
		terminalMedrano.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		terminal1.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		comuna1 = new Comuna("comuna 1",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,11), new PersistablePoint(11,11), new PersistablePoint (11,0)));
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new PersistablePoint(10,10),114);
		cgp2 = new CGP("cgp2","beiro","caballito",100,new PersistablePoint(10.1,10.1),comuna1);
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp2.agregarServicio(altaDomicilio);
		Mapa.getInstance().agregarPoi(parada114);
		Mapa.getInstance().agregarPoi(cgp2);
	}
	@After
	public void after() {
		rollbackTransaction();
	}
	@Test
	public void testGuardarBusquedas(){	
		terminalMedrano.buscar("gcp1");
		terminalMedrano.buscar("parada114");
		terminalMedrano.buscar("criterio");
		Assert.assertEquals(3,repositorioBusquedas.busquedasPorFecha().get(LocalDate.now()),0);
	} 
	
	@Test
	public void testCantidadResultadosParcialesDeBusqueda1EnTerminal1Son2(){
		terminalMedrano.buscar("a");
		terminalMedrano.buscar("cgp1");
		Assert.assertEquals(repositorioBusquedas.cantResultadosTotalesPorTerminal().get("TerminalMedrano").intValue(), 2);
	}
	
	@Test
	public void testCantidadResultadosTotalesDeTerminalSon3(){
		terminal1.buscar("a");//dos resultadods
		terminal1.buscar("cgp");//un resultado
		Assert.assertEquals(repositorioBusquedas.cantResultadosTotalesPorTerminal().get("teminal1").intValue(),3);
	}
	
	@Test
	public void testSeHizoUnaBusquedaHoyEnReportePorFecha(){
		Map<LocalDate,Long> reporte;
		terminal1.buscar("busqueda1");
		reporte = repositorioBusquedas.busquedasPorFecha();
		Assert.assertTrue(reporte.keySet().contains(LocalDate.now()));
	}
	
	@Test
	public void testCantidadDeBusquedasDeHoy(){
		Map<LocalDate,Long> reporte;
		terminal1.buscar("busqueda1");
		terminal1.buscar("busqueda2");
		terminal1.buscar("busqueda3");
		reporte = repositorioBusquedas.busquedasPorFecha();
		Assert.assertEquals(3,reporte.get(LocalDate.now()),0);
	}
	
	@Test
	public void testCantidadResultadosTotalesPorTerminal(){
		Map<String,Integer> reporte;
		terminal1.buscar("alta domicilio");
		terminal1.buscar("114");
		terminalMedrano.buscar("cgp");
		reporte = repositorioBusquedas.cantResultadosTotalesPorTerminal();
		Assert.assertEquals(2,reporte.get("teminal1"),0);
		Assert.assertEquals(1,reporte.get("TerminalMedrano"),0);
	}
	/*
	@Test
	public void testSeMandaMailConBusquedaDemorada()
	{
		ServicioMail servicio = Mockito.mock(ServicioMail.class);
		Mapa mapaMock = Mockito.mock(Mapa.class);
		Mockito.when(mapaMock.buscar("")).thenAnswer(new Answer<List<POI>>(){

				@Override
				public List<POI> answer(InvocationOnMock invocation) throws Throwable {
					Thread.sleep(2000);
					return new LinkedList<POI>();
				}
		});
		Terminal terminalConMock = new Terminal("teminal2", mapaMock);
		terminalConMock.agregarObserverBusqueda(AccionesBusqueda.NOTIFICARBUSQUEDALARGA);
		terminalConMock.buscar("");	//TODO mockito no funciona con la interfaz
		//Mockito.verify(servicio).notificarAdministrador("admin@sistema.com","Busqueda Larga","La busqueda llevó demasiado tiempo.");
	}*/
}
