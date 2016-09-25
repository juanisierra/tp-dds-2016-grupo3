package utn.dds.k3001.grupo3.tpa.busquedas;

import java.util.List;

public interface BusquedasOrigin {
public List<Busqueda> getBusquedas();
public void addBusqueda(Busqueda busqueda);
public void reset();
}
