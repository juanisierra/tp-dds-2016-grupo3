package utn.dds.k3001.grupo3.tpa.procesosProgramados;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

import utn.dds.k3001.grupo3.tpa.busquedas.*;
import utn.dds.k3001.grupo3.tpa.origenesDePOIS.RepositorioInterno;
import utn.dds.k3001.grupo3.tpa.pois.POI;

public class ActualizarLocales implements Callable<ResultadoProceso> 
{
	private ParserArchivoLocales parser;
	private RepositorioInterno repositorio;
	private int POISAfectados;
	
	public ActualizarLocales(RepositorioInterno repositorio,String filePath) throws FallaProcesoException{
		this.parser = new ParserArchivoLocales(filePath);
		this.repositorio = repositorio;
	}
	public ResultadoProceso call(){	
		this.POISAfectados = 0;
		try {
			parser.obtenerLocalYPalabrasClaves().forEach((local , palabrasClaves) -> 
					{POI localEncontrado = repositorio.buscarPorNombre(local);
					if(localEncontrado!=null){
					localEncontrado.cambiarEtiquetas(palabrasClaves);
					this.POISAfectados++;
					}
					});
		} catch (FallaProcesoException e) {
		return new ResultadoProceso(LocalDateTime.now(),POISAfectados,false,e.getMessage());
		}
		return new ResultadoProceso(LocalDateTime.now(),POISAfectados,true,"Locales actualizados correctamente");
	}
}
