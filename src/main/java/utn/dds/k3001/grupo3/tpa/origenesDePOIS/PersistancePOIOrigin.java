package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.toIntExact;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import org.uqbarproject.jpa.java8.extras.WithEntityManager;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class PersistancePOIOrigin extends AbstractPersistenceTest implements POIOrigin,WithGlobalEntityManager {

	@SuppressWarnings("unchecked")
	@Override
	public List<POI> getPOIS() {
		List<POI> pois = new LinkedList<POI>();
		withTransaction(() -> {
		pois.addAll((List<POI>)entityManager().createQuery("FROM POI").getResultList());
		});
		return pois;
	}

	@Override
	public void eliminarPOI(POI poi) {
		
		withTransaction(() -> {entityManager().remove(poi);});
	}

	@Override
	public void vaciar() {
	}

	@Override
	public void agregarPOI(POI poi) {
		withTransaction(() -> {entityManager().persist(poi);});
		

	}

	@Override
	public void eliminarPorNumero(Long id) {
	
		withTransaction(() -> {		
				entityManager().remove(entityManager().find(POI.class, id.intValue()));
		});
	}
}