package utn.dds.k3001.grupo3.tpa.origenesDePOIS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utn.dds.k3001.grupo3.tpa.pois.POI;

public class RepositorioInterno implements OrigenDeDatos {
	
	private ArrayList<POI> listaPOIS;
	
	public RepositorioInterno(){
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
}
