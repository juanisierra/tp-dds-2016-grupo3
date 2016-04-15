package utn.dds.k3001.grupo3.tpa;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class Sistema 
{	//TODO agregar metodos para agregar los POIS a la lista
	List<POI> POIS;

	public List<POI> buscar(String criterio)
	{
		return POIS.stream().filter(POI -> POI.esBuscado(criterio)).collect(Collectors.toList());
	}
}
