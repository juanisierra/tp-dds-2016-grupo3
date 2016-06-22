package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.util.List;

import utn.dds.k3001.grupo3.tpa.ObserverBusqueda;
import utn.dds.k3001.grupo3.tpa.OrigenDeTerminales;
import utn.dds.k3001.grupo3.tpa.Terminal;

public class ActualizarAcciones implements Runnable {
private List<ObserverBusqueda> accionesAAgregar;
private List<ObserverBusqueda> accionesAEliminar;
private OrigenDeTerminales terminales;
private SchedulerProcesos scheduler;
private int POISAfectados;
@Override
public void run() {
	List<Terminal> listaTerminalesAModificar = terminales.obtenerTerminales();
	listaTerminalesAModificar.forEach(terminal -> {
		accionesAAgregar.forEach(accion -> terminal.agregarObserverBusqueda(accion));
		accionesAEliminar.forEach(accion -> terminal.eliminarObserverBusqueda(accion));
	});
	scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),POISAfectados,true,"Acciones actualizadas correctamente"));

}

}
