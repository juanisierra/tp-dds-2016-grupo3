package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestsAdapterBancos {
	AdapterSistemaBancos adapter;
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
	Mapa CABA;
	@Before
	public void init(){
		CABA = new Mapa();
		requestServiceMock = Mockito.mock(RequestService.class);
		Mockito.when(requestServiceMock.getJsonBancos("la Plaza","")).thenReturn(listaBancos);
		Mockito.when(requestServiceMock.getJsonBancos("","la Plaza")).thenReturn(listaBancos);
		adapter = new AdapterSistemaBancos(requestServiceMock);
		CABA.agregarOrigenDeDatos(adapter);
	}
	@Test
	public void testElAdapterLlamaAlServicio() throws Exception{
		adapter.buscar("la Plaza");
		Mockito.verify(requestServiceMock).getJsonBancos("la Plaza","");
		Mockito.verify(requestServiceMock).getJsonBancos("","la Plaza");
		}
	@Test
	public void testElMapaEncuentra4Bancos() throws Exception{
		Assert.assertEquals(4, CABA.buscar("la Plaza").size());
		Mockito.verify(requestServiceMock).getJsonBancos("la Plaza","");
		Mockito.verify(requestServiceMock).getJsonBancos("","la Plaza");
	}
}
