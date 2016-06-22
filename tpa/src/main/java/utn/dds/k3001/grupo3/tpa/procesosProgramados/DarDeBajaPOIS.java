package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import utn.dds.k3001.grupo3.tpa.JsonFactory;
import utn.dds.k3001.grupo3.tpa.RepositorioInterno;

public class DarDeBajaPOIS implements Runnable {
	OldPOISRequestService servicio; 
	JsonFactory factory;
	RepositorioInterno repositorio;
	SchedulerProcesos scheduler;
	int poisAfectados;
	@Override
	public void run() {
	Map<Long,LocalDate> poisADarDeBaja = factory.JsonAListaPOISBaja(servicio.getJsonPOIS());
	poisADarDeBaja.forEach((numero, Fecha) -> {
		if(Fecha.isAfter(LocalDate.now()))
		{
			repositorio.eliminarPoiPorNumero(numero);
			poisAfectados++;
		}
	});
	scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),poisAfectados,true,"POIS dados de baja correctamente."));
	}
}
