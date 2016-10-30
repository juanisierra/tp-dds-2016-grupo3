package utn.dds.k3001.grupo3.tpa.tests.persistencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
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


public class PersistirTodo extends AbstractPersistenceTest implements WithGlobalEntityManager {
	@SuppressWarnings("unchecked")
	//@Test
	public void guardarYTraerPOI() {
		POI poi1 = new POI("Verduleria","Nazca","Centro",200,new PersistablePoint(2,2));
		poi1.agregarEtiqueta("A");
		poi1.agregarEtiqueta("B");
		withTransaction(() -> {
			
			entityManager().persist(poi1);
			entityManager().flush();
			});
		
		withTransaction(() -> {
		Assert.assertEquals(2, ((List<POI>)entityManager().createQuery("FROM POI").getResultList()).get(0).getListaEtiquetas().size());
		});
	
		}
	//@Test
	public void guardarYTraerDisponibilidad() {
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		withTransaction(() -> {
			
			entityManager().persist(disp);
			});
	}
	//@Test
	public void guardarYTraerLocalComercial() {
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		Rubro rubro = new Rubro("a",2);
		LocalComercial local = new LocalComercial("Pepe","calle1","Centro",12,new PersistablePoint(2,2),rubro,disp);
		withTransaction(() -> {
			
			entityManager().persist(local);
			});
	}
	//@Test
	public void guardarYTraerParadaColectivo() {
		ParadaColectivo parada = new ParadaColectivo("Parada1","calle1","Centro",12,new PersistablePoint(2,2),114);
		withTransaction(() -> {
			
			entityManager().persist(parada);
			});
	}
	//@Test
	public void guardarYTraerBanco() {
		Disponibilidad disp = Disponibilidad.lunesAViernes(LocalTime.of(2, 0), LocalTime.of(5, 0));
		Servicio servicio = new Servicio("Cheques",disp);
		Banco banco = new Banco("Banco1","calle a","Barrio1",123,new PersistablePoint(2,2));
		banco.agregarServicio(servicio);
				withTransaction(() -> {
			
			entityManager().persist(banco);
			});
	}
	//@Test
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
