package utn.dds.k3001.grupo3.tpa.tests;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;
import utn.dds.k3001.grupo3.tpa.geo.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Assert;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.JsonFactory;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.procesosProgramados.*;
public class TestsProcesosBatch {
	ParserArchivoLocales parser;
	File archivoPrueba;
	RepositorioInterno repositorioPOI;
	LocalComercial panaderia;
	ActualizarLocales actualizarLocales;
	SchedulerProcesos scheduler;
	
	@Rule
	public TemporaryFolder carpetaTemporal =new TemporaryFolder();
	
	@Before
	public void init() throws FallaProcesoException	{

		try {
			archivoPrueba = carpetaTemporal.newFile("prueba1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		scheduler = new SchedulerProcesos();
		archivoPrueba.setWritable(true);
		parser = new ParserArchivoLocales(archivoPrueba.getAbsolutePath());
		repositorioPOI = RepositorioInterno.getInstance();
		repositorioPOI.resetRepositorio();
		panaderia = new LocalComercial("panaderia","","",0,new PersistablePoint(0,0),new Rubro("panaderias",10),Disponibilidad.lunesAViernes(LocalTime.of(10, 0), LocalTime.of(15, 0)));
		repositorioPOI.agregarPoi(panaderia);
		actualizarLocales = new ActualizarLocales(repositorioPOI,archivoPrueba.getAbsolutePath());
		
	}
	@Test
	public void testPanaderiaConComida() throws IOException, FallaProcesoException	{	
		Map<String,List<String>> resultados;
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.close();
		resultados = parser.obtenerLocalYPalabrasClaves();
		Assert.assertTrue(resultados.get("panaderia").contains("comida"));
		Assert.assertTrue(resultados.get("panaderia").contains("facturas"));
	}
	@Test
	public void testSeCopianDosLocalesConPalabras() throws IOException, FallaProcesoException{	
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
	public void testSeAgregan2PalabrasAlLocal() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		actualizarLocales.call();
		Assert.assertEquals(2,repositorioPOI.buscarPorNombre("panaderia").getEtiquetas().size(),0);
	}
	@Test
	public void testSeReintenta3Veces() throws Exception{
		Callable<ResultadoProceso> procesoMock = Mockito.mock(ActualizarLocales.class);
		Mockito.when(procesoMock.call()).thenReturn(new ResultadoProceso(LocalDateTime.now(), 0, false, "fallo"));
		Reintentar reintentar = new Reintentar(2,procesoMock);
		reintentar.call();
		Mockito.verify(procesoMock,Mockito.times(3)).call();
	}
	@Test
	public void testSeHaceUnaSolaVez() throws Exception{
		Callable<ResultadoProceso> procesoMock = Mockito.mock(ActualizarLocales.class);
		Mockito.when(procesoMock.call()).thenReturn(new ResultadoProceso(LocalDateTime.now(), 0, true, "ok"));
		Reintentar reintentar = new Reintentar(2,procesoMock);
		reintentar.call();
		Mockito.verify(procesoMock,Mockito.times(1)).call();
	}
	@Test
	public void testSeEnviaMailConFallas() throws Exception{
		Callable<ResultadoProceso> procesoMock = Mockito.mock(ActualizarLocales.class);
		Mockito.when(procesoMock.call()).thenReturn(new ResultadoProceso(LocalDateTime.now(), 0, false, "fallo"));
		ServicioMail mailMock = Mockito.mock(ServicioMail.class);
		EnviarMailFallo enviarMail = new EnviarMailFallo("", mailMock, procesoMock);
		enviarMail.call();
		Mockito.verify(mailMock).enviarMail("", "Fallo un proceso", "fallo");
	}
	@Test
	public void testSeAgreganAcciones(){
		Terminal terminal = new Terminal(null, null);
		RepositorioTerminales lista = new RepositorioTerminales(Arrays.asList(terminal));
		ActualizarAcciones actualizar =new ActualizarAcciones(lista, Arrays.asList(AccionesBusqueda.GUARDARBUSQUEDA), new ArrayList<AccionesBusqueda>());
		actualizar.call();
		Assert.assertEquals(1,terminal.cantObserversBusqueda(),0);
		}
	@Test
	public void testSeEliminanAcciones(){
		RepositorioBusquedas repositorio  = RepositorioBusquedas.getInstance();
		Terminal terminal = new Terminal(null, null);
		terminal.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		Assert.assertEquals(1,terminal.cantObserversBusqueda(),0);
		RepositorioTerminales lista = new RepositorioTerminales(Arrays.asList(terminal));
		ActualizarAcciones actualizar =new ActualizarAcciones(lista, new ArrayList<AccionesBusqueda>(),Arrays.asList(AccionesBusqueda.GUARDARBUSQUEDA));
		actualizar.call();
		Assert.assertEquals(0,terminal.cantObserversBusqueda(),0);
		}
	@Test 
	public void testDarDeBajaPOI() throws JsonProcessingException, IOException, FallaProcesoException{
		repositorioPOI.resetRepositorio();
		repositorioPOI.agregarPoi(panaderia);
		Assert.assertEquals(1,repositorioPOI.getAllPOIS().size(),0);
		OldPOISRequestService mockRequest = Mockito.mock(OldPOISRequestService.class);
		Mockito.when(mockRequest.getJsonPOIS()).thenReturn("");
		JsonFactory mockfactory = Mockito.mock(JsonFactory.class);
		Mockito.when(mockfactory.obtenerPoisAEliminar("")).thenReturn(Arrays.asList(panaderia.getID()));
		DarDeBajaPOIS darDeBaja = new DarDeBajaPOIS(repositorioPOI,mockfactory,mockRequest);
		darDeBaja.call();
		Assert.assertEquals(0,repositorioPOI.getAllPOIS().size(),0);
	}
}
