package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

public class InformarResultado implements Runnable{
	private ProcesoBatch proceso;
	private SchedulerProcesos scheduler;
	public InformarResultado(ProcesoBatch proceso, SchedulerProcesos scheduler)
	{
		this.proceso = proceso;
		this.scheduler = scheduler;
	}
	@Override
	public void run() {
		try
		{
			scheduler.agregarResultado(proceso.ejecutar());
		} catch (FallaProcesoException e)
		{
			scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),0,false,e.getMessage()));
		}
		
	}
	
}
