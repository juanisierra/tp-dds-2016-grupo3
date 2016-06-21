package utn.dds.k3001.grupo3.tpa.tests;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.junit.Assert;
import utn.dds.k3001.grupo3.tpa.procesosProgramados.*;
public class TestsParseo {
	ParserArchivoLocales parser;
	File archivoPrueba;
	@Rule
	public TemporaryFolder carpetaTemporal =new TemporaryFolder();
	@Before
	public void init()
	{

		try {
			archivoPrueba = carpetaTemporal.newFile("prueba1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		archivoPrueba.setWritable(true);
		parser = new ParserArchivoLocales(archivoPrueba.getAbsolutePath());
	}
	@Test
	public void testPanaderiaConComida() throws IOException
	{	
		Map<String,List<String>> resultados;
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.close();
		
		resultados = parser.obtenerLocalYPalabrasClaves();
		Assert.assertTrue(resultados.get("panaderia").contains("comida"));
		Assert.assertTrue(resultados.get("panaderia").contains("facturas"));
	}
	@Test
	public void testSeCopianDosLocalesConPalabras() throws IOException
	{	
		Map<String,List<String>> resultados;
		PrintWriter writer = new PrintWriter(archivoPrueba);
		writer.println("panaderia; comida facturas");
		writer.println("kiosko; golosinas comida");
		writer.close();
		
		resultados = parser.obtenerLocalYPalabrasClaves();
		Assert.assertEquals(2,resultados.get("kiosko").size(),0);
		Assert.assertEquals(2, resultados.get("panaderia").size(),0);
	}
}
