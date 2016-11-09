package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.LinkedList;
import java.util.List;

public class InMemoryBusquedasOrigin implements BusquedasOrigin{
	private List<Busqueda> lista = new LinkedList<Busqueda>();
	@Override
	public List<Busqueda> getBusquedas() {
		return lista;
	}

	@Override
	public void addBusqueda(Busqueda busqueda) {
	lista.add(busqueda);
	busqueda.setId(String.valueOf(busqueda.hashCode()));
	}

	@Override
	public void reset() {
		lista = new LinkedList<Busqueda>();
		
	}

}
