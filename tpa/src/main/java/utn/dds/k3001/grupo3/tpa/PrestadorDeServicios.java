package utn.dds.k3001.grupo3.tpa;

import java.time.*;
import java.util.List;

public abstract class PrestadorDeServicios extends POI {
	//TODO agregar constructor
	List<Servicio> serviciosOfrecidos;

	private boolean hayServicio(String criterio) {
		return serviciosOfrecidos.stream().anyMatch(servicio -> servicio.nombre().contains(criterio));
	}

	public boolean esBuscado(String criterio) {
		return (this.hayServicio(criterio) || nombre.contains(criterio)); // Ademas
		// de
		// buscar
		// los
		// servicios
		// buscamos
		// su
		// nombre,
		// tambien en el banco buscamos que tenga el servicio, para complementar
		// la busqueda comun por nombre
	}

	private Servicio buscarServicio(String nombre) {
		return serviciosOfrecidos.stream().filter(servicio -> servicio.nombre().contains(nombre)).findFirst().get();
	}

	public boolean estaDisponible(LocalDateTime fechaBuscada, String servicio) {
		return this.buscarServicio(servicio).estaDisponible(fechaBuscada);
	}

	public boolean estaDisponible(LocalDateTime fechaBuscada) {
		return serviciosOfrecidos.stream().anyMatch(servicio -> servicio.estaDisponible(fechaBuscada));
	}
}
