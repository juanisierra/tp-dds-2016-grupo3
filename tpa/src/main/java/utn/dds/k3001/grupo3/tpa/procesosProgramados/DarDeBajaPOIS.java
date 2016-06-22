package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

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
	List<BajaPOI> poisADarDeBaja = factory.JsonAObjeto(servicio.getJsonPOIS(), new TypeReference<List<BajaPOI>>() {});
	poisADarDeBaja.forEach(bajaPOI -> {
			repositorio.eliminarPoiPorNumero(bajaPOI.getId());
			poisAfectados++;
		}
	);
	scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),poisAfectados,true,"POIS dados de baja correctamente."));
	}
}
