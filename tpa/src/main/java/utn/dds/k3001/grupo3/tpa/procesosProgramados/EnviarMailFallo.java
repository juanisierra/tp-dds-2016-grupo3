package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;

import utn.dds.k3001.grupo3.tpa.ServicioMail;

public class EnviarMailFallo implements Runnable {

	private ProcesoBatch proceso;
	private SchedulerProcesos scheduler;
	private ServicioMail servicio;
	private String mailAdministrador;
	public EnviarMailFallo(String mail,ServicioMail servicio,SchedulerProcesos scheduler,ProcesoBatch proceso)
	{
		this.proceso=proceso;
		this.scheduler = scheduler;
		this.servicio = servicio;
		this.mailAdministrador = mail;
	}
	@Override
	public void run() {
		try
		{
			scheduler.agregarResultado(proceso.ejecutar());
		} catch (FallaProcesoException e)
		{
			scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),0,false,e.getMessage()));
			servicio.notificarAdministrador(mailAdministrador, "Fallo en Proceso", e.getMessage());
		}
		
	}
}
