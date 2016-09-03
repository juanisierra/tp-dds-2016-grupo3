package utn.dds.k3001.grupo3.tpa.tests.persistencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import utn.dds.k3001.grupo3.tpa.*;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.geo.Point;
import utn.dds.k3001.grupo3.tpa.geo.Polygon;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class PersistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void guardarYTraerBusqueda() {
		Comuna comuna1 = new Comuna();
		comuna1.setNombre("Centro");
		Polygon poligono = new Polygon(Arrays.asList(new Point(2,2),new Point(1,1)));
		comuna1.setLimites(poligono);
		Terminal terminal = new Terminal();
		terminal.setNombre("Terminal Centro");
		terminal.setComuna(comuna1);
		Busqueda busqueda = new Busqueda(terminal,2,"a",LocalTime.now(),LocalTime.now(),LocalDate.now());		
		withTransaction(() -> {
			
			entityManager().persist(busqueda);
			});
		Assert.assertTrue(entityManager().find(Busqueda.class, 1)==busqueda);
		Assert.assertTrue(entityManager().find(Busqueda.class, 1).getTerminal()==terminal);
		Assert.assertTrue(entityManager().find(Busqueda.class, 1).getTerminal().getComuna()==comuna1);
	}
	@Test
	public void guardarYTraerPOI() {
		POI poi1 = new POI("Verduleria","Nazca","Centro",200,new Point(2,2));
		poi1.agregarEtiqueta("A");
		poi1.agregarEtiqueta("B");
		withTransaction(() -> {
			
			entityManager().persist(poi1);
			});
	}
	@Test
	public void contextUpWithTransaction() throws Exception {
		
		withTransaction(() -> {

		});
	}

}
