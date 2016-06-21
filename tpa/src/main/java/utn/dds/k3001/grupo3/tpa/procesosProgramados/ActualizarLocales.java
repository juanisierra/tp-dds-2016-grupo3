package utn.dds.k3001.grupo3.tpa.procesosProgramados;


import utn.dds.k3001.grupo3.tpa.*;
public class ActualizarLocales implements Runnable {
	ParserArchivoLocales parser;
	RepositorioInterno repositorio;
	public ActualizarLocales(RepositorioInterno repositorio,String filePath)
	{
		this.parser = new ParserArchivoLocales(filePath);
		this.repositorio = repositorio;
	}
@Override
public void run()
{
	parser.obtenerLocalYPalabrasClaves().forEach((local , palabrasClaves) -> 
			repositorio.buscar(local)
			.stream()
			.filter(POI -> POI.getClass().equals(LocalComercial.class))
			.forEach(LocalComercial -> LocalComercial.cambiarEtiquetas(palabrasClaves)));
}
}
