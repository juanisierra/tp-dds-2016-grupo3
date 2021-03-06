package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.AdapterSistemaBancos;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RequestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class TestsAdapterBancos extends AbstractPersistenceTest implements WithGlobalEntityManager, TransactionalOps, EntityManagerOps{

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
		beginTransaction();
		Mapa.getInstance().resetMapa();
		requestServiceMock = Mockito.mock(RequestService.class);
		Mockito.when(requestServiceMock.getJsonBancos("","")).thenReturn(listaBancos);
		adapter = new AdapterSistemaBancos(requestServiceMock);
	}
	
	@Test
	public void testElMapaEncuentra2Bancos() throws Exception{
		Mapa.getInstance().agregarOrigenDeDatos(adapter);
		Assert.assertEquals(2, Mapa.getInstance().buscar("la Plaza").size());
		Mockito.verify(requestServiceMock).getJsonBancos("","");
	}
	@Test
	public void testElAdapterLlamaAlServicio() throws Exception{
		adapter.buscar("la Plaza");
		Mockito.verify(requestServiceMock).getJsonBancos("","");
		}
	
}
