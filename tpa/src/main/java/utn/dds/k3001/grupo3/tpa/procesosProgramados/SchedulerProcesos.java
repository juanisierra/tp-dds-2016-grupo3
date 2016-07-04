package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerProcesos
{
	private ScheduledExecutorService executor;
	private List<ResultadoProceso> historialProcesos;
	
	public SchedulerProcesos(){
		executor = Executors.newSingleThreadScheduledExecutor();
		historialProcesos = new ArrayList<ResultadoProceso>();
	}
	public List<ResultadoProceso> getHistorial() {
	return historialProcesos;
	}
	public void agregarTarea(Callable<ResultadoProceso> tarea,LocalDateTime horaEjecucion){
	executor.schedule(new FailureManager(tarea,this), ChronoUnit.SECONDS.between(LocalDateTime.now(), horaEjecucion), TimeUnit.SECONDS);
	}
	public void registrarResultado(ResultadoProceso resultado) {
	historialProcesos.add(resultado);
	}
}
