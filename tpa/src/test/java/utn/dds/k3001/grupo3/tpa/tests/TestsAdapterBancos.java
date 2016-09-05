package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.AdapterSistemaBancos;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RequestService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestsAdapterBancos {
	Mapa CABA;
	RequestService requestServiceMock;
	public String listaBancos = "["
			+"{"
			+"\"banco\": \"Banco de la Plaza1\","
		      +"\"x\": -35.9338322,"
		      +"\"y\": 72.348353,"
		      +"\"sucursal\": \"Avellaneda\","
		      +"\"gerente\": \"Javier Loeschbor\","
		      +"\"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
		      +"},"
		      +"{"
		      +"\"banco\": \"Banco de la Plaza2\","
		      +"\"x\": -35.9345681,"
		      +"\"y\": 72.344546,"
		      +"\"sucursal\": \"Caballito\","
		      +"\"gerente\": \"Fabián Fantaguzzi\","
		      +"\"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\", \"\", \"\", \"\", \"\""
		      +"]"
		      +"}]";
	AdapterSistemaBancos adapter;
	
	@Before
	public void init(){
		CABA = Mapa.getInstance();
		CABA.resetMapa();
		requestServiceMock = Mockito.mock(RequestService.class);
		Mockito.when(requestServiceMock.getJsonBancos("","")).thenReturn(listaBancos);
		adapter = new AdapterSistemaBancos(requestServiceMock);
		
	}
	@Test
	public void testElMapaEncuentra2Bancos() throws Exception{
		CABA.agregarOrigenDeDatos(adapter);
		Assert.assertEquals(2, CABA.buscar("la Plaza").size());
		Mockito.verify(requestServiceMock).getJsonBancos("","");
	}
	@Test
	public void testElAdapterLlamaAlServicio() throws Exception{
		adapter.buscar("la Plaza");
		Mockito.verify(requestServiceMock).getJsonBancos("","");
		}
	
}
