package utn.dds.k3001.grupo3.tpa.tests.persistencia;

import java.time.LocalTime;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.*;

public class PersistTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	@Before
	public void before(){
		Mapa.getInstance().resetMapa();
		RepositorioTerminales.reset();
		RepositorioTerminales.persistirEnBD();
	}
	@After
	public void after(){
		RepositorioTerminales.persistirEnMemoria();
	}
	@Test
	public void testPersistirTerminales(){
	Terminal terminal1 = new Terminal("teminal1");
	terminal1.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
	Terminal terminal2 = new Terminal("teminal2");
	RepositorioTerminales.persistirEnBD();
	RepositorioTerminales repo = RepositorioTerminales.getInstance();
	repo.agregarTerminal(terminal1);
	repo.agregarTerminal(terminal2);
	List<Terminal> lista = repo.obtenerTerminales();
	Assert.assertEquals(terminal1.getNombre(),lista.get(0).getNombre());
	Assert.assertEquals(terminal1.getObserversBusqueda(),lista.get(0).getObserversBusqueda());
	}
	@Test
	public void testPersistirPOIS(){
		Rubro libreria = new Rubro("libreria",50);
		Disponibilidad disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		LocalComercial libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new PersistablePoint(10,10),libreria,disponibilidadLibrerias);
		RepositorioInterno.getInstance().agregarPoi(libreriaYenny);
		entityManager().flush();
		Assert.assertEquals(libreriaYenny,RepositorioInterno.getInstance().getAllPOIS().get(0));
	}
}
