package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;

public class TestReportesBusqueda
{
	Mapa CABA;
	RepositorioTerminales repositorioTerminales;
	Terminal terminal, terminalMedrano;
	Rubro libreria;
	Comuna comuna1;
	Disponibilidad disponibilidadLibrerias;
	ParadaColectivo parada114;
	CGP cgp2;
	Servicio altaDomicilio, servicio;
	
	@Before
	public void init(){	
		CABA = new Mapa();
		terminal = new Terminal("teminal1", CABA);
		terminalMedrano = new Terminal("TerminalMedrano", CABA);
		repositorioTerminales = new RepositorioTerminales();
		repositorioTerminales.agregar(terminal);
		repositorioTerminales.agregar(terminalMedrano);
		
		comuna1 = new Comuna("comuna 1",Arrays.asList(new Point(0,0), new Point(0,11), new Point(11,11), new Point (11,0)));
		disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new Point(10,10),114);
		cgp2 = new CGP("cgp2","beiro","caballito",100,new Point(10.1,10.1),comuna1);
		altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp2.agregarServicio(altaDomicilio);
		
		CABA.agregarPoi(parada114);
		CABA.agregarPoi(cgp2);
	}
	
	@Test
	public void testGuardarBusquedas(){	
		terminal.buscar("gcp1");
		terminal.buscar("parada114");
		terminal.buscar("criterio");
		Assert.assertEquals(3,terminal.busquedasEnFecha(LocalDate.now()),0);
	} 
	
	@Test
	public void testResultadosParcialesDeBusqueda1EnTerminal1Son2(){
		terminal.buscar("a");
		terminal.buscar("cgp1");
		Assert.assertEquals(terminal.resultadosParcialesDeBusquedas().get(0).intValue(), 2);
	}
	
	@Test
	public void testResultadosTotalesDeTerminal1Son3(){
		terminal.buscar("a");//dos resultadods
		terminal.buscar("cgp");//un resultado
		Assert.assertEquals(terminal.resultadosTotalesDeBusquedas(),3);
	}
	
	@Test
	public void testSeHizoUnaBusquedaHoyEnReportePorFecha(){
		Map<LocalDate,Long> reporte;
		terminal.buscar("busqueda1");
		reporte = repositorioTerminales.busquedasPorFecha();
		Assert.assertTrue(reporte.keySet().contains(LocalDate.now()));
	}
	
	@Test
	public void testCantidadDeBusquedasDeHoy(){
		Map<LocalDate,Long> reporte;
		terminal.buscar("busqueda1");
		terminal.buscar("busqueda2");
		terminal.buscar("busqueda3");
		reporte = repositorioTerminales.busquedasPorFecha();
		Assert.assertEquals(3,reporte.get(LocalDate.now()),0);
	}
	
	@Test
	public void testResultadosTotalesPorTerminal(){
		Map<Terminal,Integer> reporte;
		terminal.buscar("alta domicilio");
		terminal.buscar("114");
		terminalMedrano.buscar("cgp");
		reporte = repositorioTerminales.resultadosTotalesPorTerminal();
		Assert.assertEquals(2,reporte.get(terminal),0);
		Assert.assertEquals(1,reporte.get(terminalMedrano),0);
	}
}
