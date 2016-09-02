package utn.dds.k3001.grupo3.tpa.tests.persistencia;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import utn.dds.k3001.grupo3.tpa.*;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;

public class PersistenceTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void guardarYTraerBusqueda() {
		Comuna comuna1 = new Comuna();
		comuna1.setNombre("Centro");
		Terminal terminal = new Terminal();
		terminal.setNombre("Terminal Centro");
		terminal.setComuna(comuna1);
		Busqueda busqueda = new Busqueda(terminal,2,"a",LocalTime.now(),LocalTime.now(),LocalDate.now());		
		withTransaction(() -> {
			entityManager().persist(comuna1);
			entityManager().persist(terminal);
			entityManager().persist(busqueda);
			});
		Assert.assertTrue(entityManager().find(Busqueda.class, 1)==busqueda);
		Assert.assertTrue(entityManager().find(Busqueda.class, 1).getTerminal()==terminal);
		Assert.assertTrue(entityManager().find(Busqueda.class, 1).getTerminal().getComuna()==comuna1);
	}
	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}

}
