package utn.dds.k3001.grupo3.tpa.tests;

import utn.dds.k3001.grupo3.tpa.procesosProgramados.OldPOISRequestService;
import utn.dds.k3001.grupo3.tpa.JsonFactory;
import utn.dds.k3001.grupo3.tpa.procesosProgramados.FallaProcesoException;
import java.io.IOException;
import java.util.List;
import org.junit.*;
import com.fasterxml.jackson.core.JsonProcessingException;


public class TestOldPOISRequest {
	OldPOISRequestService requestService;
	JsonFactory factory;
	@Before
	public void init()
	{
		requestService = new OldPOISRequestService();
		factory = new JsonFactory();
	}
	@Test
	public void testSePidenBienPOIS() throws FallaProcesoException, JsonProcessingException, IOException
	{	
		List<Long> listaPOI = factory.obtenerPoisAEliminar(requestService.getJsonPOIS());
		Assert.assertEquals(2,listaPOI.size(),0);
	}

}
