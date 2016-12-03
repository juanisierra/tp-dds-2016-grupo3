package utn.dds.k3001.grupo3.tpa.tests.NoSQL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;
import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Busqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.converters.morphia.BigDecimalConverter;
import utn.dds.k3001.grupo3.tpa.converters.morphia.LocalDateConverter;
import utn.dds.k3001.grupo3.tpa.converters.morphia.LocalDateTimeConverter;
import utn.dds.k3001.grupo3.tpa.converters.morphia.LocalTimeConverter;
import utn.dds.k3001.grupo3.tpa.geo.PersistablePoint;
import utn.dds.k3001.grupo3.tpa.pois.CGP;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;
import utn.dds.k3001.grupo3.tpa.pois.Disponibilidad;
import utn.dds.k3001.grupo3.tpa.pois.LocalComercial;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import utn.dds.k3001.grupo3.tpa.pois.ParadaColectivo;
import utn.dds.k3001.grupo3.tpa.pois.Rubro;
import utn.dds.k3001.grupo3.tpa.pois.Servicio;

public class MongoDB {
	//@Test //Solo corre con mongo
public void testMorphia() {
		Mapa.getInstance().resetMapa();
		Comuna comuna1 = new Comuna("comuna 1",Arrays.asList(new PersistablePoint(0,0), new PersistablePoint(0,11), new PersistablePoint(11,11), new PersistablePoint (11,0)));
		Rubro libreria = new Rubro("libreria",50);
		Disponibilidad disponibilidadLibrerias = Disponibilidad.lunesAViernes(LocalTime.of(10,0), LocalTime.of(18,0));
		LocalComercial libreriaYenny = new LocalComercial("libreria yenny","Beiro","devoto",100,new PersistablePoint(10,10),libreria,disponibilidadLibrerias);
		ParadaColectivo parada114 = new ParadaColectivo("parada 114","Chivilcoy","devoto",1000,new PersistablePoint(10,10),114);
		CGP cgp1 = new CGP("cgp2","beiro","caballito",100,new PersistablePoint(10.1,10.1),comuna1);
		Servicio altaDomicilio = new Servicio("alta domicilio",disponibilidadLibrerias);
		cgp1.agregarServicio(altaDomicilio);
		comuna1.setNombre("Centro");
		comuna1.setLimites(Arrays.asList(new PersistablePoint(2,2),new PersistablePoint(1,1)));
		Terminal terminal = new Terminal();
		terminal.agregarObserverBusqueda(AccionesBusqueda.GUARDARBUSQUEDA);
		terminal.agregarObserverBusqueda(AccionesBusqueda.NOTIFICARBUSQUEDALARGA);
		terminal.setNombre("Terminal Centro");
		terminal.setComuna(comuna1);
		POI poi1 = new POI("Verduleria","Nazca","Centro",200,new PersistablePoint(2,2));
		poi1.agregarEtiqueta("A");
		poi1.agregarEtiqueta("B");
		
	final Morphia morphia = new Morphia();
	morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);

	morphia.getMapper().getConverters().addConverter(LocalTimeConverter.class);
	morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
	morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
	// tell Morphia where to find your classes
	// can be called multiple times with different packages or classes
	morphia.mapPackage("utn.dds.k3001.grupo3.tpa.busquedas");

	// create the Datastore connecting to the default port on the local host
	final Datastore datastore = morphia.createDatastore(new MongoClient(), "busquedas");
	datastore.ensureIndexes();
	List<POI> lista = new LinkedList<POI>();
	lista.add(poi1);
	lista.add(libreriaYenny);
	lista.add(cgp1);
	lista.add(parada114);
	Busqueda busqueda1= new Busqueda(terminal,2,"asdasd",LocalTime.now(),LocalTime.now(),LocalDate.now(),lista);
	datastore.save(busqueda1);
	//List<Busqueda> busquedaNueva= datastore.createQuery(Busqueda.class).asList();
}
}
