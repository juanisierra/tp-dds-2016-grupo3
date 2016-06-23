package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SchedulerProcesos
{
	private ScheduledExecutorService executor;
	private List<ScheduledFuture<ResultadoProceso>> historialProcesos;
	
	public SchedulerProcesos(){
		executor = Executors.newSingleThreadScheduledExecutor();
		historialProcesos = new ArrayList<ScheduledFuture<ResultadoProceso>>();
	}
	public List<ResultadoProceso> getHistorial() {
		List<ResultadoProceso> lista = new ArrayList<ResultadoProceso>();
		historialProcesos.stream().filter(future -> future.isDone()).forEach(resultado ->{
			try{
				lista.add(resultado.get());
			} catch (Exception e){
				
			}
		});
		return lista;
	}
	public void agregarTarea(Callable<ResultadoProceso> tarea,LocalDateTime horaEjecucion){
		historialProcesos.add(executor.schedule(tarea, ChronoUnit.SECONDS.between(LocalDateTime.now(), horaEjecucion), TimeUnit.SECONDS));
	}
}
