package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

public class FailureManager implements Runnable {
	Callable<ResultadoProceso> proceso;
	SchedulerProcesos scheduler;
	
	public FailureManager(Callable<ResultadoProceso> proceso,SchedulerProcesos scheduler){
		this.proceso = proceso;
		this.scheduler = scheduler;
	}
	
	@Override
	public void run() {
		try {
			ResultadoProceso resultado = proceso.call();
			scheduler.registrarResultado(resultado);
		} catch (Exception e) {
			scheduler.registrarResultado(new ResultadoProceso(LocalDateTime.now(),0, false, e.getMessage()));
		}
	}

}
