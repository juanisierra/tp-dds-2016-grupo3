package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import utn.dds.k3001.grupo3.tpa.JsonFactory;
import utn.dds.k3001.grupo3.tpa.RepositorioInterno;

public class DarDeBajaPOIS implements Runnable
{
	private OldPOISRequestService servicio; 
	private JsonFactory factory;
	private RepositorioInterno repositorio;
	private SchedulerProcesos scheduler;
	private int poisAfectados;
	
	public DarDeBajaPOIS(RepositorioInterno repositorio, SchedulerProcesos scheduler){
		this.factory = new JsonFactory();
		this.servicio = new OldPOISRequestService();
		this.repositorio = repositorio;
		this.scheduler = scheduler;
	}
	@Override
	public void run() {
		this.poisAfectados = 0;
		List<BajaPOI> poisADarDeBaja = factory.JsonAObjeto(servicio.getJsonPOIS(), new TypeReference<List<BajaPOI>>() {});
		poisADarDeBaja.forEach(bajaPOI -> {
			repositorio.eliminarPoiPorNumero(bajaPOI.getId());
			poisAfectados++;
		}
	);
	scheduler.agregarResultado(new ResultadoProceso(LocalDateTime.now(),poisAfectados,true,"POIS dados de baja correctamente."));
	}
}
