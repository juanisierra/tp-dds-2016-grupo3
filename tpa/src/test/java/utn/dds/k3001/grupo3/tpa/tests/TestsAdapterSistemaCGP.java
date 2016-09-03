package utn.dds.k3001.grupo3.tpa.tests;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.*;
import org.mockito.Mockito;
import utn.dds.k3001.grupo3.tpa.geo.*;
import utn.dds.k3001.grupo3.tpa.DTO.*;
import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.AdapterSistemaCGP;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;
import utn.dds.k3001.grupo3.tpa.tests.matchers.*;
public class TestsAdapterSistemaCGP {
	Mapa CABA;
	SistemaCGP sistemaMock;
	AdapterSistemaCGP adapter;
	RangosServicioDTO rangoLunes;
	ArrayList<RangosServicioDTO> rangosDTO;
	ServiciosDTO cambioDomicilio;
	Servicio miCambioDeDomicilio;
	ArrayList<ServiciosDTO> listaServiciosDTO;
	CentroDTO cgpCaballito;
	
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
public void testAdaptarServicio() {
	Assert.assertThat(miCambioDeDomicilio,new ServiciosIgualesDisponibilidades(adapter.adaptarServicios(listaServiciosDTO).get(0)));
}
@Test
public void testAdaptarCGP() {
	ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
	listaServicios.add(miCambioDeDomicilio);
	CGP miCGPCaballito = new CGP("CGP 1","Rivadavia 123","Caballito",0,new Point(0,0),new Comuna("1",null),listaServicios);
	Assert.assertThat(miCGPCaballito, new CGPIgualesDatos(adapter.adaptarCGP(cgpCaballito)));
	Assert.assertThat(miCGPCaballito.getServiciosOfrecidos().get(0), new ServiciosIgualesDisponibilidades(adapter.adaptarCGP(cgpCaballito).getServiciosOfrecidos().get(0)));
}
@Test
public void testBuscarCGP(){
	CABA.buscar("hola");
	Mockito.verify(sistemaMock).buscarPOIS("");
}
}
