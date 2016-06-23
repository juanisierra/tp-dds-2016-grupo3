package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import utn.dds.k3001.grupo3.tpa.*;

public class ActualizarLocales implements ProcesoBatch 
{
	private ParserArchivoLocales parser;
	private RepositorioInterno repositorio;
	private int POISAfectados;
	
	public ActualizarLocales(RepositorioInterno repositorio,String filePath) throws FallaProcesoException{
		this.parser = new ParserArchivoLocales(filePath);
		this.repositorio = repositorio;
	}

	public ResultadoProceso ejecutar(){	
		this.POISAfectados = 0;
		parser.obtenerLocalYPalabrasClaves().forEach((local , palabrasClaves) -> 
				repositorio.buscar(local)
				.stream()
				.filter(POI -> POI.getClass().equals(LocalComercial.class))
				.forEach(LocalComercial -> {
				this.POISAfectados++;
				LocalComercial.cambiarEtiquetas(palabrasClaves);
				}));
		return new ResultadoProceso(LocalDateTime.now(),POISAfectados,true,"Locales actualizados correctamente");
	}
}
