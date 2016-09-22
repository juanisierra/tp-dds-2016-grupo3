package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.List;
import static java.lang.Math.toIntExact;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public class PersistancePOIOrigin implements POIOrigin,WithGlobalEntityManager {

	@SuppressWarnings("unchecked")
	@Override
	public List<POI> getPOIS() {
		return (List<POI>)entityManager().createQuery("FROM POI").getResultList();
	}

	@Override
	public void eliminarPOI(POI poi) {
		entityManager().remove(poi);

	}

	@Override
	public void vaciar() {
		

	}

	@Override
	public void agregarPOI(POI poi) {
		entityManager().persist(poi);

	}

	@Override
	public void eliminarPorNumero(Long id) {
		entityManager().createQuery("delete POI p where p.id= :id").setParameter("id",toIntExact(id)).executeUpdate();
	}

}
