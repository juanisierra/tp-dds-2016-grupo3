package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;
import utn.dds.k3001.grupo3.tpa.ObserverBusqueda;
import utn.dds.k3001.grupo3.tpa.OrigenDeTerminales;
import utn.dds.k3001.grupo3.tpa.Terminal;

public class ActualizarAcciones implements Callable<ResultadoProceso>
{
	private List<ObserverBusqueda> accionesAAgregar;
	private List<ObserverBusqueda> accionesAEliminar;
	private OrigenDeTerminales terminales;
	private int POISAfectados;
	
	public ActualizarAcciones(OrigenDeTerminales terminales,List<ObserverBusqueda> accionesAAgregar,List<ObserverBusqueda> accionesAEliminar){
		this.accionesAAgregar = accionesAAgregar;
		this.accionesAEliminar = accionesAEliminar;
		this.terminales = terminales;
	}
	public ResultadoProceso call() {
		this.POISAfectados = 0;
		List<Terminal> listaTerminalesAModificar = terminales.obtenerTerminales();
		listaTerminalesAModificar.forEach(terminal -> {
			accionesAAgregar.forEach(accion -> terminal.agregarObserverBusqueda(accion));
			accionesAEliminar.forEach(accion -> terminal.eliminarObserverBusqueda(accion));
		});
	return new ResultadoProceso(LocalDateTime.now(),POISAfectados,true,"Acciones actualizadas correctamente");
	}
}
