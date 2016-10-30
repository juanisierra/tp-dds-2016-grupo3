package utn.dds.k3001.grupo3.tpa.tests.NoSQL;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbar.geodds.Point;

import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.AdapterSistemaBancos;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.OrigenSistemaExternoConCache;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RequestService;
import utn.dds.k3001.grupo3.tpa.pois.Banco;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

public class TestCache {

	AdapterSistemaBancos adapter;
	
	//@Before  //Solo con redis abierto
	public void init(){
		adapter =Mockito.mock(AdapterSistemaBancos.class);
		ArrayList<Servicio> listaServicios = new ArrayList<Servicio>();
		Servicio miCambioDeDomicilio = new Servicio("Cambio Domicilio",new Disponibilidad(LocalTime.of(8,0),LocalTime.of(16,0),Arrays.asList(DayOfWeek.of(1))));
		listaServicios.add(miCambioDeDomicilio);
		Banco banco1 = new Banco("asdasd","Rivadavia 123","Caballito",0,null);
		banco1.agregarServicio(miCambioDeDomicilio);
		banco1.agregarEtiqueta("asdasd");
		List<POI> ListaBancosObjeto =new LinkedList<POI>();
		ListaBancosObjeto.add(banco1);
		
		Mockito.when(adapter.buscar("")).thenReturn(ListaBancosObjeto);
	}
	//@Test
	public void testSeBusca2VecesYUnaVienedeCache() throws Exception{
		
		

		OrigenSistemaExternoConCache<Banco> origen = new OrigenSistemaExternoConCache<Banco>(adapter,"bancos",1);
		List<POI> resultado = origen.buscar("");
		Assert.assertEquals(1, origen.buscar("").size());
		Mockito.verify(adapter).buscar("");
		
	}
	
}
