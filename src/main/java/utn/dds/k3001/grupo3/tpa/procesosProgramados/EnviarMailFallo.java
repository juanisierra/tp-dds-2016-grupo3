package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.util.concurrent.Callable;

import utn.dds.k3001.grupo3.tpa.busquedas.ServicioMail;

public class EnviarMailFallo implements Callable<ResultadoProceso> 
{
	private Callable<ResultadoProceso> proceso;
	private ServicioMail servicio;
	private String mailAdministrador;
	
	public EnviarMailFallo(String mail,ServicioMail servicio,Callable<ResultadoProceso> proceso)
	{
		this.proceso=proceso;
		this.servicio = servicio;
		this.mailAdministrador = mail;
	}
	
	@Override
	public ResultadoProceso call() throws Exception{
		ResultadoProceso resultado = proceso.call();
		if(!resultado.terminoCorrectamente()){
		servicio.enviarMail(mailAdministrador, "Fallo un proceso", resultado.getDescripcion());
	}
		return resultado;
	}
}
