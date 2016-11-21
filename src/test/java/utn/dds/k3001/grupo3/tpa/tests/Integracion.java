package utn.dds.k3001.grupo3.tpa.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.dds.k3001.grupo3.tpa.geo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.JsonFactory;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.procesosProgramados.*;

public class Integracion extends AbstractPersistenceTest implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps {
	ParserArchivoLocales parser;
	File archivoPrueba;
	RepositorioInterno repositorioPOI;
	LocalComercial panaderia;
	ActualizarLocales actualizarLocales;
	SchedulerProcesos scheduler;
	OldPOISRequestService requestService;
	JsonFactory factory;
	@Rule
	public TemporaryFolder carpetaTemporal =new TemporaryFolder();
	
	@Before
	public void init() throws FallaProcesoException	{
		beginTransaction();
		try {
			archivoPrueba = carpetaTemporal.newFile("prueba1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		scheduler = new SchedulerProcesos();
		archivoPrueba.setWritable(true);
		parser = new ParserArchivoLocales(archivoPrueba.getAbsolutePath());
		repositorioPOI = RepositorioInterno.getInstance();
		RepositorioInterno.reset();
		panaderia = new LocalComercial("panaderia","","",0,new PersistablePoint(0,0),new Rubro("panaderias",10),Disponibilidad.lunesAViernes(LocalTime.of(10, 0), LocalTime.of(15, 0)));
		repositorioPOI.agregarPoi(panaderia);
		actualizarLocales = new ActualizarLocales(repositorioPOI,archivoPrueba.getAbsolutePath());
		requestService = new OldPOISRequestService("http://demo3537367.mockable.io/trash","pois");
		factory = new JsonFactory();
	}
	//@Test la url esta caida
	public void testSePidenBienPOIS() throws FallaProcesoException, JsonProcessingException, IOException
	{	
		List<Long> listaPOI = factory.obtenerPoisAEliminar(requestService.getJsonPOIS());
		Assert.assertEquals(2,listaPOI.size(),0);
	}
	//@Test
	public void testSeEjecutaActualizarLocales() throws FileNotFoundException, InterruptedException{
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		commitTransaction();
		beginTransaction();
		scheduler.agregarTarea(actualizarLocales, LocalDateTime.now()); 
		Thread.sleep(300); //Dormimos el hilo para que se llegue a ejecutar el otro
		POI poi = repositorioPOI.buscarPorNombre("panaderia");
		Assert.assertEquals(1, scheduler.getHistorial().size(),0);
		Assert.assertTrue(poi.getNombre().contains("panaderia"));
	}
	@Test
	public void testNoSeLlegaAEjecutarTarea() throws FileNotFoundException, InterruptedException{
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		scheduler.agregarTarea(actualizarLocales, OffsetDateTime.now().plusHours(1).toLocalDateTime());
		Thread.sleep(30); //Dormimos el hilo para que se llegue a ejecutar el otro
		Assert.assertEquals(0,repositorioPOI.buscarPorNombre("panaderia").getListaEtiquetas().size(),0);
		Assert.assertEquals(0, scheduler.getHistorial().size(),0);
	}
	@Test
	public void testSeCorre3VecesYSeAnota1() throws Exception{
		Callable<ResultadoProceso> procesoMock = Mockito.mock(ActualizarLocales.class);
		Mockito.when(procesoMock.call()).thenReturn(new ResultadoProceso(LocalDateTime.now(), 0, false, "fallo"));
		Reintentar reintentar = new Reintentar(2,procesoMock);
		scheduler.agregarTarea(reintentar, LocalDateTime.now());
		Thread.sleep(30);
		Mockito.verify(procesoMock,Mockito.times(3)).call();
		Assert.assertEquals(scheduler.getHistorial().size(), 1,0);
	}
}
