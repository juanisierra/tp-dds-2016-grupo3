package utn.dds.k3001.grupo3.tpa.tests;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.*;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;
import utn.dds.k3001.grupo3.tpa.*;
import utn.dds.k3001.grupo3.tpa.DTO.*;

public class TestsAdapterSistemaCGP {
	Mapa CABA;
	SistemaCGP sistemaMock;
	AdapterSistemaCGP adapter;
	RangosServicioDTO rangoLunes;
	ArrayList<RangosServicioDTO> rangosDTO;
	ServiciosDTO cambioDomicilio;
	CentroDTO cgpCaballito;
	Servicio miCambioDeDomicilio;
	ArrayList<ServiciosDTO> listaServiciosDTO;
		
@Before
public void init() {
	CABA = new Mapa();
	sistemaMock = Mockito.mock(SistemaCGP.class);
	adapter = new AdapterSistemaCGP(sistemaMock);
	rangoLunes = new RangosServicioDTO(1,8,0,16,0);
	rangosDTO = new ArrayList<RangosServicioDTO>();
	rangosDTO.add(rangoLunes);
	cambioDomicilio = new ServiciosDTO("Cambio Domicilio", rangosDTO);
	
	miCambioDeDomicilio = new Servicio("Cambio Domicilio",new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1))));
	listaServiciosDTO= new ArrayList<ServiciosDTO>(1);
	listaServiciosDTO.add(cambioDomicilio);
	cgpCaballito = new CentroDTO(1,"Caballito","Juan Pablo","Rivadavia 123","4444-4444",listaServiciosDTO);
	CABA.agregarOrigenDeDatos(adapter);
}
@Test
public void testAdaptarDisponibilidad(){
	Disponibilidad disponibilidadLunes =new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1)));
	Assert.assertTrue(disponibilidadLunes.getHoraApertura().equals(adapter.adaptarDisponibilidades(rangosDTO).get(0).getHoraApertura()));
	Assert.assertTrue(disponibilidadLunes.getHoraCierre().equals(adapter.adaptarDisponibilidades(rangosDTO).get(0).getHoraCierre()));
	Assert.assertTrue(disponibilidadLunes.getDiasDisponible().get(0).equals(adapter.adaptarDisponibilidades(rangosDTO).get(0).getDiasDisponible().get(0)));
}
@Test
public void testAdaptarServicio() {
	Assert.assertTrue(miCambioDeDomicilio.getListaDisponibilidad().get(0).getHoraApertura().equals(adapter.adaptarServicios(listaServiciosDTO).get(0).getListaDisponibilidad().get(0).getHoraApertura()));
	Assert.assertTrue(miCambioDeDomicilio.getListaDisponibilidad().get(0).getHoraCierre().equals(adapter.adaptarServicios(listaServiciosDTO).get(0).getListaDisponibilidad().get(0).getHoraCierre()));
}
@Test
public void testAdaptarCGP() {
	LinkedList<Servicio> listaServicios = new LinkedList<Servicio>();
	listaServicios.add(miCambioDeDomicilio);
	CGP miCGPCaballito = new CGP("CGP 1","Rivadavia 123","Caballito",0,new Point(0,0),new Comuna("1",null),listaServicios);
	Assert.assertTrue(miCGPCaballito.getNombre().equals(adapter.adaptarCGP(cgpCaballito).getNombre()));
	Assert.assertTrue(miCGPCaballito.getDireccion().getCalle().equals(adapter.adaptarCGP(cgpCaballito).getDireccion().getCalle()));
	Assert.assertTrue(miCGPCaballito.getDireccion().getBarrio().equals(adapter.adaptarCGP(cgpCaballito).getDireccion().getBarrio()));
	Assert.assertTrue(miCGPCaballito.getServiciosOfrecidos().get(0).getListaDisponibilidad().get(0).getHoraApertura().equals(miCambioDeDomicilio.getListaDisponibilidad().get(0).getHoraApertura()));
	Assert.assertTrue(miCGPCaballito.getServiciosOfrecidos().get(0).getListaDisponibilidad().get(0).getHoraCierre().equals(miCambioDeDomicilio.getListaDisponibilidad().get(0).getHoraCierre()));
}
@Test
public void testBuscarCGP(){
	CABA.buscar("hola");
	Mockito.verify(sistemaMock).buscarPOIS("hola");
}
}
