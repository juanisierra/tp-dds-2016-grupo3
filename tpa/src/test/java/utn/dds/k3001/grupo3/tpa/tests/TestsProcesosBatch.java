package utn.dds.k3001.grupo3.tpa.tests;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import org.junit.Assert;

import utn.dds.k3001.grupo3.tpa.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.LocalComercial;
import utn.dds.k3001.grupo3.tpa.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.Rubro;
import utn.dds.k3001.grupo3.tpa.procesosProgramados.*;
public class TestsProcesosBatch {
	ParserArchivoLocales parser;
	File archivoPrueba;
	RepositorioInterno repositorioPOI;
	LocalComercial panaderia;
	ActualizarLocales actualizarLocales;
	SchedulerProcesos scheduler;
	InformarResultado informar;
	@Rule
	public TemporaryFolder carpetaTemporal =new TemporaryFolder();
	@Before
	public void init() throws FallaProcesoException
	{

		try {
			archivoPrueba = carpetaTemporal.newFile("prueba1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		scheduler = new SchedulerProcesos();
		archivoPrueba.setWritable(true);
		parser = new ParserArchivoLocales(archivoPrueba.getAbsolutePath());
		repositorioPOI = new RepositorioInterno();
		panaderia = new LocalComercial("panaderia","","",0,new Point(0,0),new Rubro("panaderias",10),Disponibilidad.lunesAViernes(LocalTime.of(10, 0), LocalTime.of(15, 0)));
		repositorioPOI.agregarPoi(panaderia);
		actualizarLocales = new ActualizarLocales(repositorioPOI,archivoPrueba.getAbsolutePath());
		informar = new InformarResultado(actualizarLocales,scheduler);
	}
	@Test
	public void testPanaderiaConComida() throws IOException
	{	
		Map<String,List<String>> resultados;
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.close();
		
		resultados = parser.obtenerLocalYPalabrasClaves();
		Assert.assertTrue(resultados.get("panaderia").contains("comida"));
		Assert.assertTrue(resultados.get("panaderia").contains("facturas"));
	}
	@Test
	public void testSeCopianDosLocalesConPalabras() throws IOException
	{	
		Map<String,List<String>> resultados;
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		
		resultados = parser.obtenerLocalYPalabrasClaves();
		Assert.assertEquals(2,resultados.get("kiosko").size(),0);
		Assert.assertEquals(2, resultados.get("panaderia").size(),0);
	}
	@Test
	public void testSeAgregan2PalabrasAlLocal() throws FileNotFoundException
	{
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		actualizarLocales.ejecutar();
		Assert.assertEquals(2,repositorioPOI.buscarPorNombre("panaderia").get(0).getEtiquetas().size(),0);
	}
	@Test
	public void testSeEjecutaActualizarLocales() throws FileNotFoundException, InterruptedException
	{
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		scheduler.agregarTarea(informar, LocalDateTime.now());
		Thread.sleep(10); //Dormimos el hilo para que se llegue a ejecutar el otro
		Assert.assertEquals(2,repositorioPOI.buscarPorNombre("panaderia").get(0).getEtiquetas().size(),0);
		Assert.assertEquals(1, scheduler.getHistorial().size(),0);
	}
	@Test
	public void testNoSeLlegaAEjecutarTarea() throws FileNotFoundException, InterruptedException
	{
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		scheduler.agregarTarea(informar, OffsetDateTime.now().plusHours(1).toLocalDateTime());
		Thread.sleep(10); //Dormimos el hilo para que se llegue a ejecutar el otro
		Assert.assertEquals(0,repositorioPOI.buscarPorNombre("panaderia").get(0).getEtiquetas().size(),0);
		Assert.assertEquals(0, scheduler.getHistorial().size(),0);
	}
	@Test
	public void testSeReintenta3VecesYSeInforma1() throws FallaProcesoException
	{	ProcesoBatch procesoBatchMock = Mockito.mock(ProcesoBatch.class);
		Mockito.doThrow(new FallaProcesoException("No anduvo")).when(procesoBatchMock).ejecutar();
		Reintentar reintentar = new Reintentar(2,procesoBatchMock,scheduler);
		reintentar.run();
		Mockito.verify(procesoBatchMock,Mockito.times(3)).ejecutar();
		Assert.assertEquals(1,scheduler.getHistorial().size(),0);
	}
}
