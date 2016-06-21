package utn.dds.k3001.grupo3.tpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public List<POI> buscarPorNombre(String nombre)
	{
		return listaPOIS.stream().filter(POI -> POI.getNombre().equals(nombre)).collect(Collectors.toList());
	}
}
