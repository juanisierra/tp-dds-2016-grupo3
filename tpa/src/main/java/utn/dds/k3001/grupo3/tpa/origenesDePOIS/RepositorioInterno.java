package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import utn.dds.k3001.grupo3.tpa.busquedas.Mapa;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class RepositorioInterno implements OrigenDeDatos {
	
	private ArrayList<POI> listaPOIS;
	private final static RepositorioInterno INSTANCE = new RepositorioInterno();
	private RepositorioInterno(){
		listaPOIS = new ArrayList<POI>();
	}
	public static RepositorioInterno getInstance() {
		return INSTANCE;
	}
	public void resetRepositorio(){
		listaPOIS = new ArrayList<POI>();
	}
	public void agregarPoi(POI poiNvo){
		listaPOIS.add(poiNvo);
	}
	public void eliminarPoi(POI poiAEliminar){
		listaPOIS.remove(poiAEliminar);
	}
	@Override
	public List<POI> buscar(String criterio) {
		return listaPOIS.stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList());
	}
	public POI buscarPorNombre(String nombre)
	{	List<POI> lista = listaPOIS.stream().filter(POI -> POI.getNombre().equals(nombre)).collect(Collectors.toList());
		if(lista.isEmpty())
		{
			return null;
		} else {
		return lista.get(0);
		}
	}
	public void eliminarPoiPorNumero(long id) {
		listaPOIS.removeIf(POI -> Long.valueOf(POI.getID()).equals(id));
	}	
	public List<POI> getAllPOIS(){
		return listaPOIS;
	}
	@SuppressWarnings("unchecked")
	public List<POI> obtenerPOISPersistidos(){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return (List<POI>) em.createQuery("FROM POI").getResultList();
		
	}
	public void persistirPOIS(){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		listaPOIS.forEach(terminal -> em.persist(terminal));
	}
}
