package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

public class Reintentar implements Runnable {
	private ProcesoBatch proceso;
	private SchedulerProcesos scheduler;
	private int numRepeticiones;
	public Reintentar(int numeroDeVeces,ProcesoBatch proceso,SchedulerProcesos scheduler)
	{
		this.numRepeticiones = numeroDeVeces;
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
			if(numRepeticiones>0){
				numRepeticiones--;
				this.run();
			} else {
				scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),0,false,e.getMessage()));
			}
		}
		
	}

}
