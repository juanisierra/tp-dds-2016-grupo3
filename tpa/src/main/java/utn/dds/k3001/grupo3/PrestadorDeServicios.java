package utn.dds.k3001.grupo3;

import java.util.List;

public abstract class PrestadorDeServicios extends POI {
	List<Servicio> serviciosOfrecidos;
	
	private boolean hayServicio(String criterio)
	{
		return serviciosOfrecidos.stream().anyMatch(servicio -> servicio.nombre().contains(criterio));
	}
	
	public boolean esBuscado(String criterio) 
	{
		return (this.hayServicio(criterio) || nombre.contains(criterio)); //Ademas de buscar los servicios buscamos su nombre, 
		//tambien en el banco buscamos que tenga el servicio, para complementar la busqueda comun por nombre
	}
}
