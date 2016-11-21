package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Callable;

import utn.dds.k3001.grupo3.tpa.origenesDePOIS.JsonFactory;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;

public class DarDeBajaPOIS implements Callable<ResultadoProceso>
{
	private OldPOISRequestService servicio; 
	private JsonFactory factory;
	private RepositorioInterno repositorio;
	private int poisAfectados;
	
	public DarDeBajaPOIS(RepositorioInterno repositorio,JsonFactory factory,OldPOISRequestService servicio){
		this.factory = factory;
		this.servicio = servicio;
		this.repositorio = repositorio;
	}
	
	public ResultadoProceso call(){
		try{
		this.poisAfectados = 0;
		List<Long> poisADarDeBaja = factory.obtenerPoisAEliminar(servicio.getJsonPOIS());
		poisADarDeBaja.forEach(bajaPOI -> {
			repositorio.eliminarPoiPorNumero(bajaPOI);
			poisAfectados++;
		}
	);
		} catch(FallaProcesoException | IOException e) {
			return new ResultadoProceso(LocalDateTime.now(),poisAfectados,false,e.getMessage());
		}
		return new ResultadoProceso(LocalDateTime.now(),poisAfectados,true,"POIS dados de baja correctamente.");
	}
}
