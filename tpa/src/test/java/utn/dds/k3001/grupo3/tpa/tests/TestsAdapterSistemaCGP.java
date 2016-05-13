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
	AdapterSistemaCGP adapter;
	ServiciosDTO cambioDomicilio;
	RangosServicioDTO rangoLunes;
	CentroDTO cgpCaballito;
	ArrayList<ServiciosDTO> listaServiciosDTO;
	ArrayList<RangosServicioDTO> rangosDTO;
	Servicio miCambioDeDomicilio;
	SistemaCGP sistemaMock;
	Mapa CABA;
@Before
public void init() {
	CABA = new Mapa();
	sistemaMock = Mockito.mock(SistemaCGP.class);
	adapter = new AdapterSistemaCGP(sistemaMock);
	rangoLunes = new RangosServicioDTO(1,8,0,16,0);
	rangosDTO = new ArrayList<RangosServicioDTO>();
	rangosDTO.add(rangoLunes);
	cambioDomicilio = new ServiciosDTO("Cambio Domicilio", rangosDTO);
	cgpCaballito = new CentroDTO(1,"Caballito","Juan Pablo","Rivadavia 123","4444-4444",listaServiciosDTO);
	miCambioDeDomicilio = new Servicio("Cambio Domicilio",new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1))));
	listaServiciosDTO= new ArrayList<ServiciosDTO>(1);
	listaServiciosDTO.add(cambioDomicilio);
	CABA.agregarOrigenDeDatos(adapter);
}
@Test
public void testAdaptarDisponibilidad(){
	Disponibilidad disponibilidadLunes =new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1)));
	Assert.assertTrue(disponibilidadLunes.equals(adapter.adaptarDisponibilidades(rangosDTO).get(0)));
}
@Test
public void testAdaptarServicio() {
	Assert.assertTrue(miCambioDeDomicilio.equals(adapter.adaptarServicios(listaServiciosDTO).get(0)));
}
@Test
public void testElAdaptarCGP() {
	CGP miCGPCaballito = new CGP("","Rivadavia 123","Caballito",0,new Point(0,0),new Comuna("1",null));
	Assert.assertTrue(miCGPCaballito.equals(adapter.adaptarCGP(cgpCaballito)));
}
@Test
public void testBuscarCGP(){
	CABA.buscar("hola");
	Mockito.verify(sistemaMock).buscarPOIS("hola");
}
}
