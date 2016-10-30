package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import utn.dds.k3001.grupo3.tpa.converters.morphia.BigDecimalConverter;
import utn.dds.k3001.grupo3.tpa.converters.morphia.LocalDateConverter;
import utn.dds.k3001.grupo3.tpa.converters.morphia.LocalDateTimeConverter;
import utn.dds.k3001.grupo3.tpa.converters.morphia.LocalTimeConverter;

public class PersistenceBusquedasOrigin implements BusquedasOrigin {
	final Morphia morphia;
	Datastore datastore;
	final MongoClient mongoClient;

	public PersistenceBusquedasOrigin() {
		mongoClient =  new MongoClient();
		morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalTimeConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		morphia.mapPackage("utn.dds.k3001.grupo3.tpa.busquedas");
		datastore = morphia.createDatastore(mongoClient, "dds");
		datastore.ensureIndexes();
	}
	@Override
	public List<Busqueda> getBusquedas() {
		return datastore.createQuery(Busqueda.class).asList();
	}

	@Override
	public void addBusqueda(Busqueda busqueda) {
		datastore.save(busqueda);
	}

	@Override
	public void reset() {
		mongoClient.dropDatabase("dds");
		datastore = morphia.createDatastore(mongoClient, "dds");
	}

}
