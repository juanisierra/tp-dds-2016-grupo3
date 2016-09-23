package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;

import utn.dds.k3001.grupo3.tpa.busquedas.AccionesBusqueda;
import utn.dds.k3001.grupo3.tpa.busquedas.OrigenDeTerminales;
import utn.dds.k3001.grupo3.tpa.busquedas.Terminal;

public class ActualizarAcciones implements Callable<ResultadoProceso>
{
	private List<AccionesBusqueda> accionesAAgregar;
	private List<AccionesBusqueda> accionesAEliminar;
	private OrigenDeTerminales terminales;
	private int POISAfectados;
	
	public ActualizarAcciones(OrigenDeTerminales terminales,List<AccionesBusqueda> accionesAAgregar,List<AccionesBusqueda> accionesAEliminar){
		this.accionesAAgregar = accionesAAgregar;
		this.accionesAEliminar = accionesAEliminar;
		this.terminales = terminales;
	}
	public ResultadoProceso call() {
		this.POISAfectados = 0;
		List<Terminal> listaTerminalesAModificar = terminales.obtenerTerminales();
		listaTerminalesAModificar.forEach(terminal -> {
			POISAfectados++;
			accionesAAgregar.forEach(accion -> terminal.agregarObserverBusqueda(accion));
			accionesAEliminar.forEach(accion -> terminal.eliminarObserverBusqueda(accion));
		});
		return new ResultadoProceso(LocalDateTime.now(),POISAfectados,true,"Acciones actualizadas correctamente");
	}
}
