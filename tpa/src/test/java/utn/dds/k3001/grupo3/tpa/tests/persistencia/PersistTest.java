package utn.dds.k3001.grupo3.tpa.tests.persistencia;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioBusquedas;
import utn.dds.k3001.grupo3.tpa.busquedas.RepositorioTerminales;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;

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
	withTransaction(() -> {
		repo.persistirTerminales();
		entityManager().flush();
		
		});
	
	List<Terminal> lista = repo.obtenerTerminalesPersistidas();
	Assert.assertEquals(terminal1.getNombre(),lista.get(0).getNombre());
	Assert.assertEquals(terminal1.getObserversBusqueda(),lista.get(0).getObserversBusqueda());
	withTransaction(() -> {
		
		entityManager().createQuery("DELETE FROM Busqueda").executeUpdate();
		});
	}
	@Test
	public void testPersistirBusquedas(){
		Terminal terminal1 = new Terminal("teminal1", Mapa.getInstance());
	Busqueda busqueda1 = new Busqueda(terminal1,2,"a",LocalTime.now(),LocalTime.now(),LocalDate.now());
	Busqueda busqueda2 = new Busqueda();
	RepositorioBusquedas.getInstance().buscar(busqueda1);
	RepositorioBusquedas.getInstance().buscar(busqueda2);
	withTransaction(() -> {
		RepositorioBusquedas.getInstance().persistirBusquedas();
		entityManager().flush();
		
		});
	
	List<Busqueda> lista = RepositorioBusquedas.getInstance().obtenerBusquedasPersistidas();
	Assert.assertEquals(busqueda1.getCriterio(),lista.get(0).getCriterio());
	withTransaction(() -> {
	entityManager().createQuery("DELETE FROM Busqueda").executeUpdate();
	entityManager().createQuery("DELETE FROM Terminal").executeUpdate();
	
	});
	}
}
