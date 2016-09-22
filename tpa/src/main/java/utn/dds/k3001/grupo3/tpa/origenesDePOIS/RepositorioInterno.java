package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.pois.POI;
import static java.lang.Math.toIntExact;
public class RepositorioInterno implements OrigenDeDatos,WithGlobalEntityManager,TransactionalOps, EntityManagerOps {
	
	private final static RepositorioInterno INSTANCE = new RepositorioInterno();
	private RepositorioInterno(){}
	public static RepositorioInterno getInstance() {
		return INSTANCE;
	}
	public void agregarPoi(POI poiNvo){
		entityManager().persist(poiNvo);
	}
	public void eliminarPoi(POI poiAEliminar){
		entityManager().remove(poiAEliminar);
	}
	@SuppressWarnings("unchecked")
	public List<POI> buscar(String criterio) {
		return ((List<POI>)entityManager().createQuery("FROM POI").getResultList()).stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList());
	}
	public POI buscarPorNombre(String nombre)
	{	
	@SuppressWarnings("unchecked")
	List<POI> lista = ((List<POI>)entityManager().createQuery("FROM POI").getResultList()).stream().filter(POI -> POI.getNombre().contains(nombre)).collect(Collectors.toList());
		if(lista.isEmpty())
		{
			return null;
		} else {
		return lista.get(0);
		}
		
	}
	public void eliminarPoiPorNumero(long id) {
		entityManager().createQuery("delete POI p where p.id= :id").setParameter("id",toIntExact(id)).executeUpdate();
	}	
	@SuppressWarnings("unchecked")
	public List<POI> getAllPOIS(){
		return (List<POI>)entityManager().createQuery("FROM POI").getResultList();
	}
}
