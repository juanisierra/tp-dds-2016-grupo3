package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;
import utn.dds.k3001.grupo3.tpa.pois.Comuna;

public interface ComunasOrigin {

	public void agregarComuna(Comuna comuna);
	public List<Comuna> obtenerComunas();
	public void reset();
}
