package utn.dds.k3001.grupo3.tpa.tests.persistencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePolygon;
import utn.dds.k3001.grupo3.tpa.pois.Banco;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.ParadaColectivo;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;
import utn.dds.k3001.grupo3.tpa.procesosProgramados.ResultadoProceso;

public class PersistirTodo extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void guardarYTraerBusqueda() {
		Comuna comuna1 = new Comuna();
		comuna1.setNombre("Centro");
		PersistablePolygon poligono = new PersistablePolygon(Arrays.asList(new PersistablePoint(2,2),new PersistablePoint(1,1)));
		comuna1.setLimites(poligono);
		Terminal terminal = new Terminal();
		terminal.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		terminal.agregarObserverBusqueda(AccionesBusqueda.NOTIFICARBUSQUEDALARGA);
		terminal.setNombre("Terminal Centro");
		terminal.setComuna(comuna1);
		Busqueda busqueda = new Busqueda(terminal,2,"a",LocalTime.now(),LocalTime.now(),LocalDate.now());		
		withTransaction(() -> {
			entityManager().persist(busqueda);
			});
	}
	@Test
	public void guardarYTraerPOI() {
		POI poi1 = new POI("Verduleria","Nazca","Centro",200,new PersistablePoint(2,2));
		poi1.agregarEtiqueta("A");
		poi1.agregarEtiqueta("B");
		withTransaction(() -> {
			
			entityManager().persist(poi1);
			});
	}
	@Test
	public void guardarYTraerDisponibilidad() {
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		withTransaction(() -> {
			
			entityManager().persist(disp);
			});
	}
	@Test
	public void guardarYTraerLocalComercial() {
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		Rubro rubro = new Rubro("a",2);
		LocalComercial local = new LocalComercial("Pepe","calle1","Centro",12,new PersistablePoint(2,2),rubro,disp);
		withTransaction(() -> {
			
			entityManager().persist(local);
			});
	}
	@Test
	public void guardarYTraerParadaColectivo() {
		ParadaColectivo parada = new ParadaColectivo("Parada1","calle1","Centro",12,new PersistablePoint(2,2),114);
		withTransaction(() -> {
			
			entityManager().persist(parada);
			});
	}
	@Test
	public void guardarYTraerBanco() {
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		Servicio servicio = new Servicio("Cheques",disp);
		Banco banco = new Banco("Banco1","calle a","Barrio1",123,new PersistablePoint(2,2));
		banco.agregarServicio(servicio);
				withTransaction(() -> {
			
			entityManager().persist(banco);
			});
	}
	@Test
	public void guardarYTraerCGP() {
		Comuna comuna1 = new Comuna("a");
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		Servicio servicio = new Servicio("Cheques",disp);
		CGP cgpDevoto = new CGP("CGP","calle a","Barrio1",123,new PersistablePoint(2,2),comuna1);
		cgpDevoto.agregarServicio(servicio);
				withTransaction(() -> {
			
			entityManager().persist(cgpDevoto);
			});
	}

}
