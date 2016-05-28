package utn.dds.k3001.grupo3.tpa.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import utn.dds.k3001.grupo3.tpa.*;

public class TestsJSON {
	JsonFactory factory;
	Banco banco;
	public String  bancoJson =  "{"
			+"\"banco\": \"Banco de la Plaza\","
      +"\"x\": -35.9338322,"
      +"\"y\": 72.348353,"
      +"\"sucursal\": \"Avellaneda\","
      +"\"gerente\": \"Javier Loeschbor\","
      +"\"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\", \"\", \"\", \"\" ]"
      +"}";
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
	@Before
	public void init () {
		factory = new JsonFactory();
	}
	@Test
	public void testParseoBanco(){
		banco = factory.JsonAObjeto(bancoJson, new TypeReference<Banco>() {});
		Assert.assertEquals("Banco de la Plaza",banco.getNombre());
		Assert.assertEquals("cobro cheques", banco.getServiciosOfrecidos().get(0).getNombre());
	}
	@Test
	public void testParseoMuchosBancos(){
		List<Banco> bancos;
		bancos = factory.JsonAObjeto(listaBancos, new TypeReference<List<Banco>>(){});
		Assert.assertEquals("Banco de la Plaza1", bancos.get(0).getNombre());
		Assert.assertEquals("Banco de la Plaza2", bancos.get(1).getNombre());
	}
	
}
