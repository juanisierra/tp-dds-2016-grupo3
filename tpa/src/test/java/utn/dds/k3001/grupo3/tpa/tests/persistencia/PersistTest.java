package utn.dds.k3001.grupo3.tpa.tests.persistencia;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.geo.Point;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.*;

public class PersistTest extends AbstractPersistenceTest implements WithGlobalEntityManager{
	@Before
	public void before(){
		Mapa.getInstance().resetMapa();
		RepositorioBusquedas.getInstance().resetRepositorio();
	}
	@Test
	public void testPersistirTerminales(){
	Terminal terminal1 = new Terminal("teminal1", Mapa.getInstance());
	terminal1.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
	Terminal terminal2 = new Terminal("teminal2", Mapa.getInstance());
	RepositorioTerminales repo = new RepositorioTerminales();
	repo.agregarTerminal(terminal1);
	repo.agregarTerminal(terminal2);
	repo.persistirTerminales();
	entityManager().flush();
	List<Terminal> lista = repo.obtenerTerminalesPersistidas();
	Assert.assertEquals(terminal1.getNombre(),lista.get(0).getNombre());
	Assert.assertEquals(terminal1.getObserversBusqueda(),lista.get(0).getObserversBusqueda());
	}
	@Test
	public void testPersistirBusquedas(){
	Busqueda busqueda1 = new Busqueda(null,2,"a",LocalTime.now(),LocalTime.now(),LocalDate.now());
	Busqueda busqueda2 = new Busqueda();
	RepositorioBusquedas.getInstance().buscar(busqueda1);
	RepositorioBusquedas.getInstance().buscar(busqueda2);
	RepositorioBusquedas.getInstance().persistirBusquedas();
	entityManager().flush();
	List<Busqueda> lista = RepositorioBusquedas.getInstance().obtenerBusquedasPersistidas();
	Assert.assertEquals(busqueda1.getCriterio(),lista.get(0).getCriterio());
	}
	@Test
	public void testPersistirPOIS(){
		Rubro libreria = new Rubro("libreria",50);
		Disponibilidad disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		LocalComercial libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new Point(10,10),libreria,disponibilidadLibrerias);
		RepositorioInterno.getInstance().resetRepositorio();
		RepositorioInterno.getInstance().agregarPoi(libreriaYenny);
		RepositorioInterno.getInstance().persistirPOIS();
		entityManager().flush();
		List<POI> lista = RepositorioInterno.getInstance().obtenerPOISPersistidos();
		Assert.assertEquals(libreriaYenny,lista.get(0));
	}
}
