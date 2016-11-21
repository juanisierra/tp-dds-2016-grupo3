package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;

public class PersistenceComunasOrigin implements ComunasOrigin, WithGlobalEntityManager{

	@Override
	public void agregarComuna(Comuna comuna) {
		entityManager().persist(comuna);	
	}

	@Override
	public List<Comuna> obtenerComunas() {
		return (List<Comuna>) entityManager().createQuery("FROM Comuna").getResultList();
	}

	@Override
	public void reset() {
				
	}

}
