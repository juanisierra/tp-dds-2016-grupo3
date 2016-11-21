package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.List;
import java.util.stream.Collectors;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class RepositorioInterno implements OrigenDeDatos,WithGlobalEntityManager,TransactionalOps, EntityManagerOps {
	
	private final static RepositorioInterno INSTANCE = new RepositorioInterno();
	private static POIOrigin origen = new InMemoryPOIOrigin(); //ELEGIMOS SI PERSISTE EN MEMORIA O EN BASE DE DATOS
	private RepositorioInterno(){}
	
	public static RepositorioInterno getInstance() {
		return INSTANCE;
	}
	
	public void agregarPoi(POI poiNvo){
		origen.agregarPOI(poiNvo);;
	}
	
	public void eliminarPoi(POI poiAEliminar){
		origen.eliminarPOI(poiAEliminar);
	}
	
	public List<POI> buscar(String criterio) {
		return origen.getPOIS().stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList());
	}
	
	public POI buscarPorNombre(String nombre){	
		List<POI> lista = origen.getPOIS().stream().filter(POI -> POI.getNombre().contains(nombre)).collect(Collectors.toList());
		if(lista.isEmpty()){
			return null;
		} else {
		return lista.get(0);
		}
	}
	
	public void eliminarPoiPorNumero(long id) {
		origen.eliminarPorNumero(id);
	}
	
	public List<POI> getAllPOIS(){
		return origen.getPOIS();
	}
	
	public static void reset() {
		origen.vaciar();
	}
	
	public static void origenMemoria() {
		origen = new InMemoryPOIOrigin();
	}
	
	public static void origenPersistencia() {
		origen = new PersistancePOIOrigin();
	}
}
